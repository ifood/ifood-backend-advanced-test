package io.brunodoescoding.controllers;

import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import io.brunodoescoding.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaylistSuggestionController {

    @Autowired
    SuggestionService suggestionService;

    @RequestMapping(path = "/playlist/pick",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> pick(@RequestParam(name = "city", required = false) String city,
                             @RequestParam(name = "lat", required = false) String lat,
                             @RequestParam(name = "lon", required = false) String lon) {

        PlaylistSuggestionDto params = PlaylistSuggestionDto.builder()
                                            .city(city).lat(lat)
                                            .lon(lon).build();

        return suggestionService.pickSongs(params);
    }

}
