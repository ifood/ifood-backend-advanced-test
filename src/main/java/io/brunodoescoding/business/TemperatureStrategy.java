package io.brunodoescoding.business;

import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import io.brunodoescoding.service.TemperatureService;
import org.springframework.web.client.HttpClientErrorException;

public interface TemperatureStrategy {
    public boolean accepts(PlaylistSuggestionDto data);
    public double retrieve(PlaylistSuggestionDto data) throws HttpClientErrorException;
    public void setTemperatureService(TemperatureService temperatureService);
}
