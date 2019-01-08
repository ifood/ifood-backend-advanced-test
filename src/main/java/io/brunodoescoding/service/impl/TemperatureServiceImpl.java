package io.brunodoescoding.service.impl;

import com.google.common.base.Strings;
import com.google.common.cache.LoadingCache;
import io.brunodoescoding.dto.temperature.WeatherDto;
import io.brunodoescoding.service.TemperatureService;
import io.brunodoescoding.utils.SafeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Base64;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    private final RestTemplate restTemplate;

    private final String baseUri;
    private final String appId;

    private final LoadingCache<String, String> cache;

    @Autowired
    public TemperatureServiceImpl(RestTemplate restTemplate,
                                  @Qualifier("temperatureCache") LoadingCache<String, String> cache) {
        this.restTemplate = restTemplate;

        this.baseUri = "http://api.openweathermap.org/data/2.5/weather";
        this.appId = "REPLACE_TOKEN_HERE";

        this.cache = cache;
    }

    private String generateHash(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    @Override
    public double findByCity(String city) throws HttpClientErrorException {
        if(Strings.isNullOrEmpty(city)) {
            throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, null, null,
                    "City parameter must not be null nor empty.".getBytes(),
                    Charset.forName("UTF-8"));
        }

        String cachedTemperature = cache.getIfPresent(generateHash(city));
        if(cachedTemperature != null) {
            System.out.println(String.format("[city:%s][temperature:%s] Cache hit!", city, cachedTemperature));
            return Double.valueOf(cachedTemperature);
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri)
                                                           .queryParam("q", city)
                                                           .queryParam("units", "metric")
                                                           .queryParam("APPID", appId);

        ResponseEntity<WeatherDto> found = restTemplate.getForEntity(builder.build().toUriString(), WeatherDto.class);
        if(found.getStatusCodeValue() == HttpStatus.OK.value()) {
            double currentTemperature = found.getBody().getMain().get("temp");
            System.out.println(String.format("[city:%s][temperature:%s] Found!", city, currentTemperature));

            cache.put(generateHash(city), String.valueOf(currentTemperature));
            return currentTemperature;
        }

        if(found.getStatusCodeValue() == HttpStatus.NOT_FOUND.value()) {
            throw HttpClientErrorException.create(HttpStatus.NOT_FOUND, null, null,
                    String.format("City %s not found.", city).getBytes(),
                    Charset.forName("UTF-8"));
        }

        throw HttpClientErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, null, null,
                                              String.format("Internal problems retrieving temperature for city %s.", city).getBytes(),
                                              Charset.forName("UTF-8"));
    }

    @Override
    public double findByCoordinates(String lat, String lon) throws HttpClientErrorException {
        if(Strings.isNullOrEmpty(lat) || Strings.isNullOrEmpty(lon) ||
                !SafeParser.isDouble(lat) || !SafeParser.isDouble(lon)) {

            throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, null, null,
                    "Lat and Lon parameter must not be null nor empty and also they need to be valid numbers.".getBytes(),
                    Charset.forName("UTF-8"));

        }

        String cacheKey = String.format("%s,%s", lat, lon);
        String cachedTemperature = cache.getIfPresent(generateHash(cacheKey));
        if(cachedTemperature != null) {
            System.out.println(String.format("[lat:%s][lon:%s][temperature:%s] Cache hit!", lat, lon, cachedTemperature));
            return Double.valueOf(cachedTemperature);
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", "metric")
                .queryParam("APPID", appId);

        ResponseEntity<WeatherDto> found = restTemplate.getForEntity(builder.build().toUriString(), WeatherDto.class);
        if(found.getStatusCodeValue() == HttpStatus.OK.value()) {
            double currentTemperature = found.getBody().getMain().get("temp");
            System.out.println(String.format("[lat:%s][long:%s][temperature:%s] Found!", lat, lon, currentTemperature));

            cache.put(generateHash(cacheKey), String.valueOf(currentTemperature));
            return found.getBody().getMain().get("temp");
        }

        if(found.getStatusCodeValue() == HttpStatus.NOT_FOUND.value()) {
            throw HttpClientErrorException.create(HttpStatus.NOT_FOUND, null, null,
                    String.format("Temperature not found for coordinates [lat:%s,lon:%s].", lat, lon).getBytes(),
                    Charset.forName("UTF-8"));
        }

        throw HttpClientErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, null, null,
                String.format("Internal problems retrieving temperature for coordinates [lat:%s,lon:%s].", lat, lon).getBytes(),
                Charset.forName("UTF-8"));
    }

}
