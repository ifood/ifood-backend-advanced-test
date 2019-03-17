package br.com.ifood.challenge.celsiustracks.service;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import org.springframework.data.domain.Pageable;

public interface CelsiusTracksService {

    CelsiusPlaylist getTracksByCity(String cityName, final Pageable page);

    CelsiusPlaylist getTracksByCoordinates(Double latitude, Double longitude, final Pageable page);

}
