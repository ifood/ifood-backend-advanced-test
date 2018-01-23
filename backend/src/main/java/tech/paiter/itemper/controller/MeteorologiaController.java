package tech.paiter.itemper.controller;

import net.aksingh.owmjapis.api.APIException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.paiter.itemper.models.Playlist;
import tech.paiter.itemper.services.OpenWeatherMapService;
import tech.paiter.itemper.services.SpotifyService;

import java.io.IOException;

@RestController
@RequestMapping(value="meteorologia")
public class MeteorologiaController {

    @Autowired
    OpenWeatherMapService openWeatherMapService;

    @Autowired
    SpotifyService spotifyService;

    @GetMapping
    public @ResponseBody Playlist playlistCity(@RequestParam("c") String cidadeNome) {

        try {
            Double temperatura = openWeatherMapService.getTemperatura(cidadeNome);

            spotifyService.getPlaylistByTemp(temperatura);

            return null ;

        } catch (APIException ioe) {
            throw new ResourceNotFoundException("ERROR 1 :: No momento um de nossos sistemas não esta respondendo, tente logo mais.");
        } catch (IOException e) {
            throw new ResourceNotFoundException("ERROR 2 :: Aguarde um momento e tente logo mais.");
        }

    }

    @GetMapping(path = "coordenadas")
    public @ResponseBody Playlist playlistLatLog(@RequestParam("lat") String lat, @RequestParam("log") String log) {

        openWeatherMapService.getLatLong(lat, log);

        return null;
    }



}
