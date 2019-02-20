package com.ifood.test.controller;


import com.ifood.test.dto.Coord;
import com.ifood.test.service.SpotifyService;
import com.ifood.test.service.TracksWheatherSpotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/tracks")
@Slf4j
public class WheatherTracksController {

    private static final String playlistId = "37i9dQZF1DX5BAPG29mHS8";
    @Autowired
    TracksWheatherSpotifyService wheatherSpotifyService;
    @Autowired
    private SpotifyService spotifyService;

    @RequestMapping(value = "/place", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Object> getTracksByPlace(@RequestBody String place) {
        final List<String> tracksByPlace = wheatherSpotifyService.getTracksByPlace(place);
        return new ResponseEntity<Object>(tracksByPlace, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/coord", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Object> getTracksByPlace(@RequestBody Coord coord) {
        final List<String> tracksByPlace = wheatherSpotifyService.getTracksByCord(coord);
        return new ResponseEntity<Object>(tracksByPlace, new HttpHeaders(), HttpStatus.OK);
    }


}
