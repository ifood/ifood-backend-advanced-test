package mx.sindelantal.hyxtrix.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;

import mx.sindelantal.hyxtrix.model.ClientInformation;
import mx.sindelantal.hyxtrix.model.Song;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Service
@CacheConfig(cacheNames= {"inputCache"})
public class InputServiceImpl implements InputService{

	protected static final Logger LOG = LogManager.getLogger();
	private final RestTemplate restTemplate;
	private final static double KELVIN = 273.15; 
	private static Map<String,List<Song>> mpCache;
	static {
		mpCache = new HashMap<String, List<Song>>();
		mpCache.put("defaul",  new ArrayList<Song>(
			      Arrays.asList(new Song("DefaultSong"))));
	}
	@Autowired
	public OWM owm;
	
	@Autowired
	SpotifyApi spotifyApi;
	
	public InputServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@HystrixCommand(fallbackMethod = "fallBackMethodByCityName", commandProperties = { 
			@HystrixProperty (name = "fallback.enabled", value = "true"), 
			@HystrixProperty (name = "circuitBreaker.enabled", value = "false"), 
			@HystrixProperty (name = "circuitBreaker.requestVolumeThreshold", value = "200"),
			@HystrixProperty (name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			})
	@Cacheable(value = "cacheByCityName")
	@Override
	public List<Song> findByCityName(String cityName) throws APIException, SpotifyWebApiException, IOException {
		List<Song> songs = null;
		LOG.info("findByCityName {}",cityName);
        CurrentWeather cwd = owm.currentWeatherByCityName(cityName);
        if (cwd.hasRespCode() && cwd.getRespCode() == 200) {
            if (cwd.hasCityName() && cwd.hasMainData() && cwd.getMainData().hasTemp()) {
                songs = findTypeByTemperature(kelvinToCelcius(cwd.getMainData().getTemp()));
                mpCache.put(cityName, songs);
            }
        }
		return songs;
	}
	
	@HystrixCommand(fallbackMethod = "fallBackMethodByTemperature", commandProperties = { 
			@HystrixProperty (name = "fallback.enabled", value = "true"), 
			@HystrixProperty (name = "circuitBreaker.enabled", value = "false"), 
			@HystrixProperty (name = "circuitBreaker.requestVolumeThreshold", value = "200")
			})
	@Cacheable(value = "cacheByTemperature")
	private List<Song> findTypeByTemperature(Double temperature) throws SpotifyWebApiException, IOException{
		LOG.info("findTypeByTemperature {}",temperature);
		List<Song> songs = null;
		String playlistsKey=null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ClientInformation> entity = new HttpEntity<ClientInformation>(new ClientInformation(temperature), headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8090/temperature/",
				HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
				});
		playlistsKey = response.getBody();
		songs = findPlayListByKey(playlistsKey);
		return songs;
	}

	@HystrixCommand(fallbackMethod = "fallBackMethodByPlayList", commandProperties = { 
			@HystrixProperty (name = "fallback.enabled", value = "true"), 
			@HystrixProperty (name = "circuitBreaker.enabled", value = "false"), 
			@HystrixProperty (name = "circuitBreaker.requestVolumeThreshold", value = "200")
			})
	@Cacheable(value = "cacheByPlayList")
	private List<Song> findPlayListByKey(String playlistsKey) throws SpotifyWebApiException, IOException {
		LOG.info("Start SpotifyApi {}",playlistsKey);
        
        GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi.getPlaylistsTracks(playlistsKey).build();
        Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsTracksRequest.execute();
        List<Song> lstSong = Arrays.stream(playlistTrackPaging.getItems()).map(playlistTrack -> new Song(playlistTrack.getTrack().getName())).collect(Collectors.toList()); 
        mpCache.put(playlistsKey, lstSong);
		return lstSong;
	}
	
	@HystrixCommand(fallbackMethod = "fallBackMethodByCoordinates", commandProperties = { 
			@HystrixProperty (name = "fallback.enabled", value = "true"), 
			@HystrixProperty (name = "circuitBreaker.enabled", value = "false"), 
			@HystrixProperty (name = "circuitBreaker.requestVolumeThreshold", value = "200"),
			@HystrixProperty (name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			})
	@Cacheable(value = "cacheByCoordinates")
	@Override
	public List<Song> findByCoordinates(double latitude,double longitud) throws APIException, SpotifyWebApiException, IOException {
		LOG.info("findByCoordinates.");
		List<Song> songs = null;        
        CurrentWeather cwd = owm.currentWeatherByCoords(latitude, longitud);
        if (cwd.hasRespCode() && cwd.getRespCode() == 200) {
            if (cwd.hasCoordData() && cwd.hasMainData() && cwd.getMainData().hasTemp()) {
                songs = findTypeByTemperature(kelvinToCelcius(cwd.getMainData().getTemp()));
            }
        }
		return songs;
	}
	
	private static Double kelvinToCelcius(Double kelvin) {
		return kelvin - KELVIN;
	}

	@SuppressWarnings("unused")
	private List<Song> fallBackMethodByCityName(String cityName) {
		LOG.warn("circuitBreaker fallBackMethodByCityName {}",cityName);
		return cacheDefaultList(cityName);
	}

	@SuppressWarnings("unused")
	private List<Song> fallBackMethodByTemperature (Double temperature){
		LOG.warn("circuitBreaker fallBackMethodByCoordinates {}",temperature);
		return mpCache.get("defaul");
	}
	@SuppressWarnings("unused")
	private List<Song> fallBackMethodByCoordinates(double latitude,double longitud) {
		LOG.warn("circuitBreaker fallBackMethodByCoordinates.");
		return mpCache.get("defaul");
	}
	
	@SuppressWarnings("unused")
	private List<Song> fallBackMethodByPlayList(String playlistsKey) {
		LOG.warn("circuitBreaker fallBackMethodByPlayList {}",playlistsKey);
		return cacheDefaultList(playlistsKey);
	}
	
	@Cacheable(value = "cacheDefaultList")
	private List<Song> cacheDefaultList(String playlistsKey) {
		return mpCache.containsKey(playlistsKey)?mpCache.get(playlistsKey):mpCache.get("defaul");
	}
}
