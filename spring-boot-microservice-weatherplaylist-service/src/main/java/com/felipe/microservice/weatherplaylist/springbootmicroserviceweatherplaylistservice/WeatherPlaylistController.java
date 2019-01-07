package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.bo.WeatherPlaylistBO;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.exception.BusinessException;

/**
 * Weather playlist service controller
 * 
 * @author ffrazato
 */
@RestController
public class WeatherPlaylistController {

    @Autowired
    private WeatherPlaylistBO weatherPlaylistBO;

    /**
     * Service responsible for retrieving the playlist track names based in the current temperature for a given city name
     *
     * @param cityName
     *            to get the current temperature
     * @return playlist track names
     */
    @GetMapping("/playlist/{cityName}")
    public List<String> getPlayListByCityName(@PathVariable String cityName) {
        List<String> playlistSoundTracks = null;
        try {

            playlistSoundTracks = weatherPlaylistBO.getPlaylistTrackNamesByCityName(cityName);

        } catch (BusinessException be) {
            // TODO: handle this excetping to retrieve something useful to the client
        }

        return playlistSoundTracks;
    }

    /**
     * Service responsible for retrieving the playlist track names based in the current temperature for given geographic coordinates
     *
     * @param latitude
     * @param longitude
     * @return playlist track names
     */
    @GetMapping("/playlist/{latitude}/{longitude}")
    public List<String> getPlayListByGeoCoordinates(@PathVariable double latitude, @PathVariable double longitude) {
        List<String> playlistSoundTracks = null;
        try {

            playlistSoundTracks = weatherPlaylistBO.getPlaylistTrackNamesByGeoCoordinates(latitude, longitude);

        } catch (BusinessException be) {
            // TODO: handle this excetping to retrieve something useful to the client
        }

        return playlistSoundTracks;
    }
}
