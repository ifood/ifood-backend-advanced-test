package ifood.controller;

import ifood.exception.BaseException;
import ifood.model.SpotifyTrackData;
import ifood.service.PlaylistBuilderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Playlist builder API - (OpenWeather / Spotify)")
@RequestMapping("/playlists")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistBuilderController {

    private static final String SPOTIFY_HEADER_KEY = "Spotify-Token";

    private final PlaylistBuilderService service;

    @ApiOperation(
            value = "Busca por nome da cidade",
            notes = "Retorna um conjunto de faixas de acordo com a temperatura na cidade desejada (case insensitive)." +
                    "<br/><br/><b>IMPORTANTE:</b>\n" +
                    "- As músicas serão buscadas em playlists do país.\n" +
                    "- O valor Earth (case insensitive): as faixas serão buscadas de acordo com a temperatura média da " +
                    "Terra e, neste caso, a busca de faixas não se restringe a um país.")
    @GetMapping(value = "/city/{cityname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpotifyTrackData> searchByCityname(@PathVariable final String cityname,
                                                   @RequestHeader(value = SPOTIFY_HEADER_KEY) final String token) {
            return service.getTracksByLocation(cityname, null, null, token);
    }

    @ApiOperation(
            value = "Busca por valores de latitude e longitude",
            notes = "Retorna um conjunto de faixas de acordo com a temperatura na cidade da posição geográfica fornecida.\n" +
                    "<br/><br/><b>IMPORTANTE:</b>\n" +
                    "- Latitude: de -90 até 90.\n" +
                    "- Longitude: de -180 até 180.\n" +
                    "- Valores lat=0 ou lon=0: as faixas serão buscadas de acordo com a temperatura média da Terra e, " +
                    "neste caso, a busca de faixas não se restringe a um país.")
    @GetMapping(value = "/lat/{lat}/lon/{lon}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpotifyTrackData> searchByGeo(@PathVariable final Double lat,
                                              @PathVariable final Double lon,
                                              @RequestHeader(value = SPOTIFY_HEADER_KEY) final String token) {
        return service.getTracksByLocation(null, lat, lon, token);
    }
}
