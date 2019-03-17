package br.com.ifood.challenge.celsiustracks.controller;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusTracksResource;
import br.com.ifood.challenge.celsiustracks.service.CelsiusTracksService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/celsiustracks")
public class CelsiusTracksController {

    private final CelsiusTracksService celsiusTracksService;

    @GetMapping(value = "/cities/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelsiusTracksResource> getTracksByCity(@PathVariable("cityName") final String cityName,
                                                                 @RequestParam(value = "pageNumber", defaultValue = "0") final Integer pageNumber,
                                                                 @RequestParam(value = "limit", defaultValue = "10") final Integer limit) {
        final CelsiusPlaylist celsiusPlaylist = celsiusTracksService.getTracksByCity(cityName, PageRequest.of(pageNumber, limit));
        final CelsiusTracksResource celsiusTracksResource = new CelsiusTracksResource(celsiusPlaylist);
        return ResponseEntity.ok(celsiusTracksResource);
    }

    @GetMapping(value = "/coordinates/latitudes/{latitude}/longitudes/{longitude}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CelsiusTracksResource> getTracksByCoordinates(@PathVariable("latitude") final Double latitude,
                                                                        @PathVariable("longitude") Double longitude,
                                                                        @RequestParam(value = "pageNumber", defaultValue = "0") final Integer pageNumber,
                                                                        @RequestParam(value = "limit", defaultValue = "1") final Integer limit) {
        final CelsiusPlaylist celsiusPlaylist = celsiusTracksService.getTracksByCoordinates(latitude, longitude, PageRequest.of(pageNumber, limit));
        final CelsiusTracksResource celsiusTracksResource = new CelsiusTracksResource(celsiusPlaylist);
        return ResponseEntity.ok(celsiusTracksResource);
    }

}
