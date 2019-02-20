package com.ifood.test.service.impl;

import com.ifood.test.dto.Coord;
import com.ifood.test.dto.Place;
import com.ifood.test.service.TracksWheatherSpotifyService;
import com.ifood.test.service.WeatherService;
import com.ifood.test.service.WheatherTracks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TracksWheatherSpotifyServiceImpl implements TracksWheatherSpotifyService {

    @Autowired
    private WeatherService weatherService;

    private WheatherTracks wheatherTracks;


    @Override
    public List<String> getTracksByPlace(String place) {
        final Place weatherPlace = weatherService.getWeatherPlace(place);
        final Double temperature = weatherPlace.getWeather().getTemperature();
        final List<String> tracks = evaluateTemperature(temperature);
        return tracks;
    }

    @Override
    public List<String> getTracksByCord(Coord coord) {
        final Place weatherPlace = weatherService.getWeatherCoord(coord);
        final Double temperature = weatherPlace.getWeather().getTemperature();
        final List<String> tracks = evaluateTemperature(temperature);
        return tracks;
    }


    private List<String> evaluateTemperature(Double temperature) {
        if (temperature > 30) {
            wheatherTracks = WheatherTracks.PARTY;
        } else if (temperature > 15 && temperature < 30) {
            wheatherTracks = WheatherTracks.POP;
        } else if (temperature > 10 && temperature < 14) {
            wheatherTracks = WheatherTracks.ROCK;
        } else if (temperature < 14) {
            wheatherTracks = WheatherTracks.CLASSICAL;
        }
        return wheatherTracks.getTracksByWhether();
    }


}
