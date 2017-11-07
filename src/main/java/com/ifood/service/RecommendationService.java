package com.ifood.service;

import com.ifood.api.OpenWeatherMapAPI;
import com.ifood.api.SpotifyAPI;
import com.ifood.businessrules.CategoryConversion;
import com.ifood.entity.Track;
import com.wrapper.spotify.exceptions.WebApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * Provide the service unique service for Recommendation.
 */
@Service
public class RecommendationService {
    @Autowired
    private OpenWeatherMapAPI weatherAPI;

    @Autowired
    private SpotifyAPI songAPI;

    @Autowired
    private CategoryConversion category;

    private float temperature;

    private final Logger logger = LoggerFactory.getLogger(RecommendationService.class);

    /**
     * Call the API to get the temperature, translate the temperature in a category, and
     *  get the tracks based on the category.
     *
     * @param args Input parameters (city name or location parameters)
     * @return List of Tracks
     */
    public Collection<Track> getRecommendation(String ... args) {
        try {
            if (args.length == 1)
                temperature = weatherAPI.getTemperatureByCity(args[0]);
            else
                temperature = parseArgsCallingTemperatureByLocation(args);
        } catch (OpenWeatherMapAPI.CityNotFoundException e) {
            logger.error(e.getMessage());
            return Collections.EMPTY_LIST;
        } catch (NullPointerException | NumberFormatException e) {
            logger.error(e.getMessage());
            throw new NumberFormatException();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new WeatherAPINotAvailableException("Error to retrieve temperature.");
        }

        return GetTracksForTemperature();
    }

    private float parseArgsCallingTemperatureByLocation(String[] args) throws
            OpenWeatherMapAPI.CityNotFoundException, NumberFormatException, NullPointerException, IOException {
        float latitude = Float.valueOf(args[0]);
        float longitude = Float.valueOf(args[1]);
        return weatherAPI.getTemperatureByLocation(latitude, longitude);
    }

    private Collection<Track> GetTracksForTemperature() {
        final String category = this.category.getCategory(temperature);
        Collection<Track> out = tryToGetTracks(category);

        for (int i = 0; i < 2 && out.isEmpty(); ++i) {
            songAPI.reconnect();
            out = tryToGetTracks(category);
        }

        return out;
    }

    private Collection<Track> tryToGetTracks(String category) {
        try {
            songAPI.connect();
        } catch (IOException | WebApiException e) {
            logger.error(e.getMessage());
            throw new SongAPINotAvailableException("Error to retrieve the tracks.");
        }

        try {
            return songAPI.getTrackByCategory(category);
        } catch (IOException | WebApiException e) {
            logger.error(e.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * Signalize problem with weather api.
     */
    public class WeatherAPINotAvailableException extends RuntimeException {
        public WeatherAPINotAvailableException(String message) {
            super(message);
        }
    }

    /**
     * Signalize problem with song (track searcher) api.
     */
    public class SongAPINotAvailableException extends RuntimeException {
        public SongAPINotAvailableException(String message) {
            super(message);
        }
    }
}
