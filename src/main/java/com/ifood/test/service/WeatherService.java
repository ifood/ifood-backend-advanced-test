package com.ifood.test.service;

import com.ifood.test.dto.Coord;
import com.ifood.test.dto.Place;

public interface WeatherService {
    Place getWeatherPlace(String place);
    Place getWeatherCoord(Coord coord);
}
