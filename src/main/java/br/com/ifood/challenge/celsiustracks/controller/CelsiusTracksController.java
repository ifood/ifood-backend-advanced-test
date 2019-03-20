package br.com.ifood.challenge.celsiustracks.controller;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusTracksResource;
import br.com.ifood.challenge.celsiustracks.service.CelsiusTracksService;
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

    @ApiOperation(value = "Retrieve a list of tracks based on the weather of a city")
    @GetMapping(value = "/cities/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelsiusTracksResource> getTracksByCity(@PathVariable("cityName") final String cityName,
                                                                 @RequestParam(value = "pageNumber", defaultValue = "0") final Integer pageNumber,
                                                                 @RequestParam(value = "limit", defaultValue = "10") final Integer limit) {
        final CelsiusPlaylist celsiusPlaylist = celsiusTracksService.getTracksByCity(cityName, PageRequest.of(pageNumber, limit));
        final CelsiusTracksResource celsiusTracksResource = new CelsiusTracksResource(celsiusPlaylist);
        return ResponseEntity.ok(celsiusTracksResource);
    }

    @ApiOperation(value = "Retrieve a list of tracks based on the weather of a coordinate (latitude, longitude)")
    @GetMapping(value = "/coordinates/latitudes/{latitude}/longitudes/{longitude}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelsiusTracksResource> getTracksByCoordinates(@PathVariable("latitude") final Double latitude,
                                                                        @PathVariable("longitude") final Double longitude,
                                                                        @RequestParam(value = "pageNumber", defaultValue = "0") final Integer pageNumber,
                                                                        @RequestParam(value = "limit", defaultValue = "1") final Integer limit) {
        validateLatitude(latitude);
        validateLongitude(longitude);

        final CelsiusPlaylist celsiusPlaylist = celsiusTracksService.getTracksByCoordinates(latitude, longitude, PageRequest.of(pageNumber, limit));
        final CelsiusTracksResource celsiusTracksResource = new CelsiusTracksResource(celsiusPlaylist);
        return ResponseEntity.ok(celsiusTracksResource);
    }

    private void validateLatitude(final Double latitude) {
        if (latitude < -90d || latitude > 90d) {
            throw new IllegalArgumentException("The latitude coordinate must be a value between -90 and 90");
        }
    }

    private void validateLongitude(final Double longitude) {
        if (longitude < -180d || longitude > 180d) {
            throw new IllegalArgumentException("The longitude coordinate must be a value between -180 and 180");
        }
    }

}
