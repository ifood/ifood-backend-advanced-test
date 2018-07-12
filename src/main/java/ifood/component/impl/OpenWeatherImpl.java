package ifood.component.impl;

import ifood.component.OpenWeather;
import ifood.model.OpenWeatherResponse;
import ifood.validator.CityValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
public class OpenWeatherImpl implements OpenWeather {

    private final String appKey;

    private final RestTemplate restTemplate;

    private final String openWeatherBaseEndpoint;

    @Autowired
    public OpenWeatherImpl(@Value("${openWeather.endpoint}") final String openWeatherEndpoint,
                           @Value("${openWeather.key}") final String appKey,
                           final RestTemplate restTemplate) {
        this.appKey = appKey;
        this.restTemplate = restTemplate;
        this.openWeatherBaseEndpoint = openWeatherEndpoint;
    }

    private URI getWeatherUri(final String cityname, final Double lat, final Double lon) {
        CityValidator.validate(cityname, lat, lon);

        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(openWeatherBaseEndpoint);
        uriBuilder.queryParam("appid", appKey).queryParam("units", "metric");

        return StringUtils.isBlank(cityname)
            ? uriBuilder.queryParam("lat", lat).queryParam("lon", lon).build().toUri()
            : uriBuilder.queryParam("q", cityname).build().toUri();
    }

    @Override
    public OpenWeatherResponse getCityTemp(final String cityname, final Double lat, final Double lon) {
        try {
            return restTemplate.getForObject(getWeatherUri(cityname, lat, lon), OpenWeatherResponse.class);
        } catch (HttpClientErrorException hcee) {
            if (hcee.getStatusCode() != HttpStatus.NOT_FOUND) {
                log.error(hcee.getMessage(), hcee);
                throw hcee;
            }
        }
        return new OpenWeatherResponse("", 0, "");
    }
}
