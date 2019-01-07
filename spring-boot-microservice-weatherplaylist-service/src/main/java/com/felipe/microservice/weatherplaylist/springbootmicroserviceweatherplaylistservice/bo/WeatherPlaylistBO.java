package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.exception.BusinessException;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.PlaylistServiceProxy;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.WeatherServiceProxy;

/**
 * Handle business logic for weather playlist service
 * 
 * @author ffrazato
 */
@Service
public class WeatherPlaylistBO {

    @Autowired
    private PlaylistServiceProxy playListServiceProxy;

    @Autowired
    private WeatherServiceProxy weatherServiceProxy;

    /**
     * Get the playlist track names based on the current temperature given a city name
     *
     * @param cityName
     * @return playlist track names
     */
    public List<String> getPlaylistTrackNamesByCityName(String cityName) {
        List<String> playlistSoundTracks = null;

        try {

            double temperature = weatherServiceProxy.retrieveCurrentTemperatureByCity(cityName);
            playlistSoundTracks = getPlaylistSoundTrackNames(temperature);

        } catch (Exception e) {
            // TODO log the exception
            // TODO something that we can do here is to get a previous value from a kind of cache
            throw new BusinessException("Problems getting data from other microservices", e);
        }

        return playlistSoundTracks;
    }

    /**
     * Get the playlist track names based on the current temperature given geographic coordinates
     *
     * @param cityName
     * @return playlist track names
     */
    public List<String> getPlaylistTrackNamesByGeoCoordinates(double latitude, double longitude) {
        List<String> playlistSoundTracks = null;

        try {

            double temperature = weatherServiceProxy.retrieveCurrentTemperatureByGeoCoordinates(latitude, longitude);
            playlistSoundTracks = getPlaylistSoundTrackNames(temperature);

        } catch (Exception e) {
            // TODO log the exception
            // TODO something that we can do here is to get a previous value from a kind of cache
            throw new BusinessException("Problems getting data from other microservices", e);
        }

        return playlistSoundTracks;
    }

    /**
     * Get the soundtrack names for the current temperature
     * 
     * @param temperature
     *            current temperature
     * @return playlist sound tracks for the current temperature
     */
    private List<String> getPlaylistSoundTrackNames(double temperature) {
        List<String> playlistSoundTracks;

        if (temperature > 30D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre("PARTY");
        } else if (temperature >= 15D && temperature <= 30D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre("POP");
        } else if (temperature >= 10D && temperature <= 14D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre("ROCK");
        } else {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre("CLASSICAL");
        }

        return playlistSoundTracks;
    }
}
