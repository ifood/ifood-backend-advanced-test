package br.com.ifood.challenge.celsiustracks.controller;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusTracksResource;
import br.com.ifood.challenge.celsiustracks.service.CelsiusTracksService;
import br.com.ifood.challenge.celsiustracks.validator.CoordinateValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "/celsiustracks", description = "API to retrieve a list of tracks based on the weather")
@RestController
@RequiredArgsConstructor
@RequestMapping("/celsiustracks")
public class CelsiusTracksController {

    private final CelsiusTracksService celsiusTracksService;
    private final CoordinateValidator coordinateValidator;

    @ApiOperation(value = "Retrieve a list of tracks based on the weather of a city")
    @GetMapping(value = "/cities/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelsiusTracksResource> getTracksByCity(@PathVariable("cityName") final String cityName,
                                                                 @RequestParam(value = "pageNumber", defaultValue = "0") final Integer pageNumber,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10") final Integer pageSize) {
        final CelsiusPlaylist celsiusPlaylist = celsiusTracksService.getTracksByCity(cityName, PageRequest.of(pageNumber, pageSize));
        final CelsiusTracksResource celsiusTracksResource = new CelsiusTracksResource(celsiusPlaylist);
        return ResponseEntity.ok(celsiusTracksResource);
    }

    @ApiOperation(value = "Retrieve a list of tracks based on the weather of a coordinate (latitude, longitude)")
    @GetMapping(value = "/coordinates/latitudes/{latitude}/longitudes/{longitude}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelsiusTracksResource> getTracksByCoordinates(@PathVariable("latitude") final Double latitude,
                                                                        @PathVariable("longitude") final Double longitude,
                                                                        @RequestParam(value = "pageNumber", defaultValue = "0") final Integer pageNumber,
                                                                        @RequestParam(value = "pageSize", defaultValue = "1") final Integer pageSize) {
        coordinateValidator.validateCoordinates(latitude, longitude);

        final CelsiusPlaylist celsiusPlaylist = celsiusTracksService.getTracksByCoordinates(latitude, longitude, PageRequest.of(pageNumber, pageSize));
        final CelsiusTracksResource celsiusTracksResource = new CelsiusTracksResource(celsiusPlaylist);
        return ResponseEntity.ok(celsiusTracksResource);
    }



}
