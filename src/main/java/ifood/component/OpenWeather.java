package ifood.component;

import ifood.exception.BaseException;
import ifood.exception.ExceptionOriginEnum;
import ifood.exception.InvalidCityException;
import ifood.exception.OpenWeatherInvalidResponseException;
import ifood.model.OpenWeatherResponse;
import ifood.validator.CityValidator;
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
public class OpenWeather {

    private final String appKey;

    private final RestTemplate restTemplate;

    private final String openWeatherBaseEndpoint;

    @Autowired
    public OpenWeather(@Value("${openWeather.endpoint}") final String openWeatherEndpoint,
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

    public OpenWeatherResponse getCityTemp(final String cityname, final Double lat, final Double lon) {
        final URI uri = getWeatherUri(cityname, lat, lon);
        try {
            return restTemplate.getForObject(uri, OpenWeatherResponse.class);
        } catch (HttpClientErrorException hcee) {
            if (HttpStatus.NOT_FOUND.equals(hcee.getStatusCode())) {
                throw new InvalidCityException(cityname, lat, lon, hcee);
            }
            throw new OpenWeatherInvalidResponseException(uri.toString(), hcee);
        } catch (Exception ex) {
            throw new BaseException(ex.getMessage(), ex, ExceptionOriginEnum.INTERNAL);
        }
    }
}
