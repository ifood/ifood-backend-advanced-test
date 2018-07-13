package ifood.controller;

import ifood.model.*;
import ifood.service.PlaylistBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderController {

    private final PlaylistBuilderService service;

    @GetMapping(value = "/weather", produces = "application/json")
    public WeatherResponse getWeather(final String cityname, final Double lat, final Double lon) {
        return service.getWeather(cityname, lat, lon);
    }

    @GetMapping(value = "/playlist", produces = "application/json")
    public SpotifyPlaylistResponse getPlaylist(final String category, final String country, final String token) {
        return service.getPlaylist(TrackCategoryEnum.valueOf(category.toUpperCase()), country, token);
    }

    @GetMapping(value = "/tracks", produces = "application/json")
    public SpotifyTracksResponse getTracks(final String playlistId, final String token) {
        return service.getTracks(playlistId, token);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public List<SpotifyTrackData> search(final String cityname, final Double lat, final Double lon, final String token) {
        return service.getTracksByLocation(cityname, lat, lon, token);
    }
}
