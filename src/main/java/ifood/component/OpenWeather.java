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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@CacheConfig(cacheNames = {"OpenWeather"})
public class OpenWeather {

    private static final String APPID_KEY = "appid";
    private static final String UNITS_KEY = "units";
    private static final String UNITS_VALUE = "metric";
    private static final String LAT_KEY = "lat";
    private static final String LON_KEY = "lon";
    private static final String QUERY_KEY = "q";

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
        uriBuilder.queryParam(APPID_KEY, appKey).queryParam(UNITS_KEY, UNITS_VALUE);

        return StringUtils.isBlank(cityname)
                ? uriBuilder.queryParam(LAT_KEY, lat).queryParam(LON_KEY, lon).build().toUri()
                : uriBuilder.queryParam(QUERY_KEY, cityname).build().toUri();
    }

    @Cacheable
    public OpenWeatherResponse getCityTemp(final String cityname, final Double lat, final Double lon) {
        final URI uri = getWeatherUri(cityname, lat, lon);
        try {
            return restTemplate.getForObject(uri, OpenWeatherResponse.class);
        } catch (HttpClientErrorException hcee) {
            if (HttpStatus.NOT_FOUND.equals(hcee.getStatusCode()) || HttpStatus.BAD_REQUEST.equals(hcee.getStatusCode())) {
                throw new InvalidCityException(cityname, lat, lon, hcee);
            }
            throw new OpenWeatherInvalidResponseException(uri.toString(), hcee);
        } catch (Exception ex) {
            throw new BaseException(ex.getMessage(), ex, ExceptionOriginEnum.INTERNAL);
        }
    }
}
