package ifood.component;

import ifood.model.OpenWeatherResponse;

public interface OpenWeather {

    OpenWeatherResponse getCityTemp(final String cityname);

    OpenWeatherResponse getCityTemp(final Double lat, final Double lon);
}
