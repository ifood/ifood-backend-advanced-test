package tech.paiter.itemper.controller;

import net.aksingh.owmjapis.api.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.paiter.itemper.apis.dto.Musica;
import tech.paiter.itemper.models.Coordenadas;
import tech.paiter.itemper.services.OpenWeatherMapService;
import tech.paiter.itemper.services.SpotifyService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="meteorologia")
public class MeteorologiaController {

    @Autowired
    OpenWeatherMapService openWeatherMapService;

    @Autowired
    SpotifyService spotifyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Musica> playlistCity(@RequestParam("c") String cidadeNome) {

        try {
            Double temperatura = openWeatherMapService.getTemperatura(cidadeNome);
            return spotifyService.getPlaylistByTemp(temperatura);

        } catch (APIException ioe) {
            throw new ResourceNotFoundException("ERROR 1 :: No momento um de nossos sistemas não esta respondendo, tente logo mais.");
        } catch (IOException e) {
            throw new ResourceNotFoundException("ERROR 2 :: Aguarde um momento e tente logo mais.");
        }
    }

    @GetMapping(path = "/lat/{lat}/log/{log}")
    public @ResponseBody List<Musica> playlistLatLog(@PathVariable("lat") String lat, @PathVariable("log") String log) {

        try {
            Coordenadas coord = new Coordenadas(Double.parseDouble(lat), Double.parseDouble(log));
            Double temperatura = openWeatherMapService.getLatLong(coord);
            return spotifyService.getPlaylistByTemp(temperatura);

        } catch (IOException e) {
            throw new ResourceNotFoundException("ERROR 3 :: Aguarde um momento e tente logo mais.");
        } catch (APIException e) {
            throw new ResourceNotFoundException("ERROR 4 :: Aguarde um momento e tente logo mais.");
        } catch (NumberFormatException nfe) {
            throw new ResourceNotFoundException("ERROR 5 :: Ops... as coordenadas estão com algum problema, de uma olhada.");
        }

    }



}
