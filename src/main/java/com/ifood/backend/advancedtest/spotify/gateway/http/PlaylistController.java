package com.ifood.backend.advancedtest.spotify.gateway.http;

import com.ifood.backend.advancedtest.openWeather.config.exception.OpenWeatherMapCityNotFoundException;
import com.ifood.backend.advancedtest.spotify.domain.Track;
import com.ifood.backend.advancedtest.spotify.usecases.GetSongsByCityUseCase;
import com.ifood.backend.advancedtest.spotify.usecases.GetSongsByLatLongUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@Api(description = "Rest API for playlist operations", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlaylistController {

    @Autowired
    private GetSongsByCityUseCase getSongsByCityUseCase;

    @Autowired
    private GetSongsByLatLongUseCase getSongsByLatLongUseCase;

    @ApiOperation(value = "Get playlists by latitude and longitude")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = {"latitude","longitude"})
    public ResponseEntity getSongs(@RequestParam(name = "latitude") float latitude, @RequestParam(name = "longitude") float longitude) {

        try {
            List<Track> tracks = getSongsByLatLongUseCase.execute(latitude, longitude);
            return ResponseEntity.status(HttpStatus.OK).body(tracks);
        } catch(OpenWeatherMapCityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }
    }

    @ApiOperation(value = "Get playlists by a city name")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = {"city"})
    public ResponseEntity getSongs(@RequestParam(name = "city") String city) {

        try {
            List<Track> tracks = getSongsByCityUseCase.execute(city);
            return ResponseEntity.status(HttpStatus.OK).body(tracks);
        } catch(OpenWeatherMapCityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }
    }
}
