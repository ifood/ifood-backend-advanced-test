package br.com.ifood.challenge.celsiustracks.service;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusTracksResource;

public interface CelsiusTracksService {

    CelsiusTracksResource getTracksByCity(String cityName);

    CelsiusTracksResource getTracksByCoordinates(Double latitude, Double longitude);

}
