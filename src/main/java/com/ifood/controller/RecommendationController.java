package com.ifood.controller;

import com.ifood.entity.Track;
import com.ifood.service.RecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Flow Controller to /recommendation.
 */
@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    private RecommendationService service;

    private final Logger logger = LoggerFactory.getLogger(RecommendationController.class);

    /**
     * Handle /recommendation/city
     * @param cityName City name
     * @return List of tracks or errors
     */
    @RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
    public ResponseEntity getTracksByCity(@PathVariable("city") String cityName) {
        logger.info("GetTracksByCity for {}", cityName);
        return performRequest(cityName);
    }

    /**
     * Handle /recommendation/location
     * @param latitude Place latitude
     * @param longitude Place longitude
     * @return List of tracks or errors
     */
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public ResponseEntity getTracksByLocation(
            @RequestParam(value = "latitude") String latitude,
            @RequestParam(value = "longitude") String longitude) {
        logger.info("GetTracksByLocation for {},{}", latitude, longitude);
        return performRequest(latitude, longitude);
    }

    private ResponseEntity performRequest(String ... location) {
        Collection<Track> out;
        try {
            out = service.getRecommendation(location);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem parsing the input.");
        } catch (RecommendationService.WeatherAPINotAvailableException e ) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Problem retrieving the temperature.");
        } catch (RecommendationService.SongAPINotAvailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Problem retrieving the tracks.");
        }

        return new ResponseEntity(out, HttpStatus.OK);
    }
}
