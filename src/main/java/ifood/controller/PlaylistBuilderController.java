package ifood.controller;

import ifood.model.SpotifyTrackData;
import ifood.service.PlaylistBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderController {

    private static final String SPOTIFY_HEADER_KEY = "Spotify-Token";

    private final PlaylistBuilderService service;

    @GetMapping(value = "/city/{cityname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpotifyTrackData> searchByCityname(@PathVariable final String cityname,
                                                   @RequestHeader(value = SPOTIFY_HEADER_KEY) final String token) {
        return service.getTracksByLocation(cityname, null, null, token);
    }

    @GetMapping(value = "/lat/{lat}/lon/{lon}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpotifyTrackData> searchByGeo(@PathVariable final Double lat,
                                              @PathVariable final Double lon,
                                              @RequestHeader(value = SPOTIFY_HEADER_KEY) final String token) {
        return service.getTracksByLocation(null, lat, lon, token);
    }
}
