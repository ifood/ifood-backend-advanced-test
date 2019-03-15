package br.com.ifood.challenge.celsiustracks.service;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CelsiusTracksService {

    List<CelsiusPlaylist> getTracksByCity(String cityName, final Pageable page);

    List<CelsiusPlaylist> getTracksByCoordinates(Double latitude, Double longitude, final Pageable page);

}
