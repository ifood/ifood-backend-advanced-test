package ifood.component;

import ifood.model.OpenWeatherResponse;

public interface OpenWeather {

    OpenWeatherResponse getCityTemp(final String cityname, final Double lat, final Double lon);
}
