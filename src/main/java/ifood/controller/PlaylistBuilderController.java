package ifood.controller;

import ifood.dto.SpotifyPlaylistResponse;
import ifood.dto.WeatherResponse;
import ifood.service.PlaylistBuilderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public SpotifyPlaylistResponse getPlaylist(final String category, final String country, final String token) {

        return service.getPlaylist(category, country, token);
    }
}
