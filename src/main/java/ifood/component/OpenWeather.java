package ifood.component;

import ifood.dto.OpenWeatherResponse;

public interface OpenWeather {

    OpenWeatherResponse getCityTemp(final String cityname);

    OpenWeatherResponse getCityTemp(final Double lat, final Double lon);
}
