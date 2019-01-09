package br.com.imood.web.service;

import br.com.imood.domain.dto.PlayListEnvelopeDTO;
import br.com.imood.domain.dto.TrackInfoDTO;
import br.com.imood.domain.enums.MusicGenre;
import br.com.imood.error.InvalidGenreException;
import br.com.imood.error.service.ExternalServiceException;
import br.com.imood.error.service.OpenWeatherServiceException;
import br.com.imood.error.service.ServiceUnavailableException;
import br.com.imood.error.service.SpotifyServiceException;
import br.com.imood.integration.service.OpenWeatherMapService;
import br.com.imood.integration.service.SpotifyService;
import br.com.imood.util.ValidationUtils;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodService {

    private OpenWeatherMapService openWeatherMapService;
    private SpotifyService spotifyService;

    public MoodService(OpenWeatherMapService openWeatherMapService, SpotifyService spotifyService){
        this.openWeatherMapService = openWeatherMapService;
        this.spotifyService = spotifyService;
    }

    /**
     * Returns a playlist based on the mood of the city provided.
     * @param city - city name
     * @return a playlist (collection of tracks)
     * @throws InvalidGenreException if a genre based on the city temperature could not be found
     * @throws ExternalServiceException if something goes wrong when consuming a web service
     */
    public List<TrackInfoDTO> getMood(String city) throws InvalidGenreException, ExternalServiceException {
        MusicGenre genre = getGenre(city);
        return getTracks(genre);
    }

    /**
     * Returns a playlist based on the mood of the city provided.
     * @param lat latitude
     * @param lon longitude
     * @return a playlist (collection of tracks)
     * @throws InvalidGenreException if a genre based on the city temperature could not be found
     * @throws ExternalServiceException if something goes wrong when consuming a web service
     */
    public List<TrackInfoDTO> getMood(Double lat, Double lon) throws InvalidGenreException, ExternalServiceException {
        MusicGenre genre = getGenre(lat, lon);
        return getTracks(genre);
    }

    /**
     * Gets a genre based on the city's temperature.
     * @param city - city name
     * @return a music genre
     * @throws InvalidGenreException if a genre based on the city temperature can not be found
     * @throws ExternalServiceException if something goes wrong when consuming a web service
     */
    private MusicGenre getGenre(String city) throws InvalidGenreException, ExternalServiceException {
        if(ValidationUtils.isNull(city)){
            throw new OpenWeatherServiceException("City name is necessary.");
        }
        try {
            return MusicGenre.from(openWeatherMapService.getWeather(city).getTemperature());
        } catch (FeignException feignException){
            treatException(feignException);
            throw new OpenWeatherServiceException("City with name %s could not be found.", city);
        }
    }

    /**
     * Gets a genre based on the city's temperature.
     * @param lat latitude
     * @param lon longitude
     * @return a music genre
     * @throws InvalidGenreException if a genre based on the city temperature can not be found
     * @throws ExternalServiceException if something goes wrong when consuming a web service
     */
    private MusicGenre getGenre(Double lat, Double lon) throws InvalidGenreException, ExternalServiceException {
        if(ValidationUtils.isNull(lat, lon)){
            throw new OpenWeatherServiceException("Both city coordinates are necessary.");
        }
        try {
            return MusicGenre.from(openWeatherMapService.getWeather(lat, lon).getTemperature());
        } catch (FeignException feignException){
            treatException(feignException);
            throw new OpenWeatherServiceException("City with coordinates %.2f and %.2f could not be found.", lat, lon);
        }
    }

    /**
     * Return a list of tracks based on the genre provided.
     * @param genre - MusicGenre
     * @return a list of tracks
     * @throws ExternalServiceException if something goes wrong when consuming a web service
     */
    private List<TrackInfoDTO> getTracks(MusicGenre genre) throws ExternalServiceException {
        PlayListEnvelopeDTO playList = getPlayListById(genre.getDescription());
        String playlistId = getFirstPlayListId(playList);
        try {
            return spotifyService.getTracks(playlistId).getItems();
        } catch (FeignException feignException){
            treatException(feignException);
            throw new SpotifyServiceException("Could not get tracks for playlist with id %s.", playlistId);
        }
    }

    /**
     * Returns a specific spotify playlist based on it's id.
     * @param playlistId - Id of the playlist
     * @return a spotify playlist
     * @throws ExternalServiceException if something goes wrong when consuming a web service
     */
    private PlayListEnvelopeDTO getPlayListById(String playlistId) throws ExternalServiceException {
        try {
            return spotifyService.getPlayList(playlistId.toLowerCase());
        } catch (FeignException feignException){
            treatException(feignException);
            throw new SpotifyServiceException("Playlist with id %s could not be found.", playlistId);
        }
    }

    /**
     * Returns the id of the first playlist in the playlist list.
     * @param playListEnvelopeDTO - Object containing all playlists and it's items.
     * @return the playlist id.
     */
    private String getFirstPlayListId(PlayListEnvelopeDTO playListEnvelopeDTO){
        return playListEnvelopeDTO.getPlaylist().getItems().get(0).getId();
    }

    /**
     * Treat a exception thrown by the consumption of some web service
     * @param feignException - exception thrown
     * @throws ServiceUnavailableException if the service response was not within the expected status
     */
    private void treatException(FeignException feignException) throws ServiceUnavailableException {
        verifyErrorRequest(feignException);
    }

    /**
     * Throws an exception if the service response was not within the expected status.
     * @param feignException - exception thrown
     * @throws ServiceUnavailableException if the service response was not within the expected status
     */
    private void verifyErrorRequest(FeignException feignException) throws ServiceUnavailableException{
        if(!isExpectedStatus(feignException.status())){
            throw new ServiceUnavailableException("Something went wrong when consuming the web service.");
        }
    }

    /**
     * Validate an Http status.
     * @param status http status
     * @return true if the status code is expected.
     */
    private boolean isExpectedStatus(int status){
        return status == HttpStatus.NOT_FOUND.value() || status == HttpStatus.BAD_REQUEST.value();
    }

}
