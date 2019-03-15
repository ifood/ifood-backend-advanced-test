package br.com.ifood.challenge.celsiustracks.controller;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusTracksResource;
import br.com.ifood.challenge.celsiustracks.service.CelsiusTracksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/celsiustracks")
public class CelsiusTracksController {

    private final CelsiusTracksService celsiusTracksService;

    @GetMapping(value = "/cities/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelsiusTracksResource> getTracksByCity(@PathVariable("cityName") final String cityName) {
        final CelsiusTracksResource celsiusTracksResource = celsiusTracksService.getTracksByCity(cityName);
        //TODO
        return ResponseEntity.ok(celsiusTracksResource);
    }

    @GetMapping(value = "/coordinates/latitudes/{latitude}/longitudes/{longitude}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelsiusTracksResource> getTracksByCoordinates(@PathVariable("latitude") final Double latitude,
                                                                        @PathVariable("longitude") Double longitude) {
        final CelsiusTracksResource celsiusTracksResource = celsiusTracksService.getTracksByCoordinates(latitude, longitude);
        //TODO
        return ResponseEntity.ok(celsiusTracksResource);
    }

}
