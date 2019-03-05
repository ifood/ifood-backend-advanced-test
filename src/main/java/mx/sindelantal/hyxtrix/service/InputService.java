package mx.sindelantal.hyxtrix.service;

import java.io.IOException;
import java.util.List;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import mx.sindelantal.hyxtrix.model.Song;
import net.aksingh.owmjapis.api.APIException;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
public interface InputService {

	/**
	 * @param cityName
	 * @return
	 * @throws APIException
	 * @throws IOException 
	 * @throws SpotifyWebApiException 
	 */
	public List<Song> findByCityName(String cityName) throws APIException, SpotifyWebApiException, IOException;
	
	/**
	 * @throws IOException 
	 * @throws SpotifyWebApiException 
	 * @throws APIException 
	 * @param latitude,longitud
	 * @return
	 * @throws 
	 */
	public List<Song> findByCoordinates(double latitude,double longitud) throws APIException, SpotifyWebApiException, IOException;
}
