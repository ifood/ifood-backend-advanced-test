package ifood.controller;

import ifood.dto.*;
import ifood.service.PlaylistBuilderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

        if (StringUtils.isNotBlank(cityname)) {
            return service.getTemp(cityname);
        } else {
            return service.getTemp(lat, lon);
        }
    }

    @GetMapping(value = "/playlist", produces = "application/json")
    public SpotifyPlaylistResponse getPlaylist(final TrackCategory category, final String country, final String token) {
        return service.getPlaylist(category, country, token);
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
