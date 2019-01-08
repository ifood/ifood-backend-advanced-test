package io.brunodoescoding.business.impl.temperature;

import io.brunodoescoding.business.TemperatureStrategy;
import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import io.brunodoescoding.service.TemperatureService;
import org.springframework.web.client.HttpClientErrorException;

public class CoordinatesBasedTemperatureImpl implements TemperatureStrategy {

    TemperatureService temperatureService;

    @Override
    public void setTemperatureService(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @Override
    public boolean accepts(PlaylistSuggestionDto data) {
        return data != null && data.isValidCoordinates();
    }

    @Override
    public double retrieve(PlaylistSuggestionDto data) throws HttpClientErrorException {
        return temperatureService.findByCoordinates(data.getLat(), data.getLon());
    }
}
