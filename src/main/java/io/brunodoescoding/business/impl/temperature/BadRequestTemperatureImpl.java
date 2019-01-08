package io.brunodoescoding.business.impl.temperature;

import io.brunodoescoding.business.TemperatureStrategy;
import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import io.brunodoescoding.service.TemperatureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

public class BadRequestTemperatureImpl implements TemperatureStrategy {

    TemperatureService temperatureService;

    @Override
    public boolean accepts(PlaylistSuggestionDto data) {
        return data == null || !data.isValid();
    }

    @Override
    public void setTemperatureService(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @Override
    public double retrieve(PlaylistSuggestionDto data) throws HttpClientErrorException {
        throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, null, null,
                                              "Required parameters city or coordinates. Coordinate values must be valid.".getBytes(),
                                               Charset.forName("UTF-8"));
    }
}
