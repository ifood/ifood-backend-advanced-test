package br.com.ifood.challenge.celsiustracks.service.impl;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusTracksResource;
import br.com.ifood.challenge.celsiustracks.service.CelsiusTracksService;
import org.springframework.stereotype.Service;

@Service
public class CelsiusTracksServiceImpl implements CelsiusTracksService {

    @Override
    public CelsiusTracksResource getTracksByCity(final String cityName) {
        return null; //TODO
    }

    @Override
    public CelsiusTracksResource getTracksByCoordinates(final Double latitude, final Double longitude) {
        return null; //TODO
    }

}
