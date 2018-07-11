package ifood.component.impl;

import ifood.component.OpenWeather;
import ifood.dto.OpenWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
//@Profile({"production", "development"})
@Slf4j
public class OpenWeatherImpl implements OpenWeather {

    private String appKey;

    private RestTemplate restTemplate;

    private UriComponentsBuilder uriBuilder;

    @Autowired
    public OpenWeatherImpl(@Value("${openWeather.endpoint}") final String openWeatherEndpoint,
                           @Value("${openWeather.key}") final String appKey,
                           final RestTemplate restTemplate) {
        this.appKey = appKey;
        this.restTemplate = restTemplate;
        this.uriBuilder = UriComponentsBuilder.fromUriString(openWeatherEndpoint);
    }

    @Override
    public OpenWeatherResponse getCityTemp(final String cityname) {
        try {
            URI uri = uriBuilder
                    .queryParam("q", cityname)
                    .queryParam("appid", appKey)
                    .queryParam("units", "metric")
                    .build().toUri();

            log.info(uri.toString());
            return restTemplate.getForObject(uri, OpenWeatherResponse.class);
        } catch (HttpClientErrorException hcee) {
            if (hcee.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw hcee;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new OpenWeatherResponse("", 0);
    }

    @Override
    public OpenWeatherResponse getCityTemp(final Double lat, final Double lon) {
        try {
            URI uri = uriBuilder
                    .queryParam("lat", lat)
                    .queryParam("lon", lon)
                    .queryParam("appid", appKey)
                    .queryParam("units", "metric")
                    .build().toUri();

            log.info(uri.toString());
            return restTemplate.getForObject(uri, OpenWeatherResponse.class);
        } catch (HttpClientErrorException hcee) {
            if (hcee.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw hcee;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new OpenWeatherResponse("", 0);
    }
}
