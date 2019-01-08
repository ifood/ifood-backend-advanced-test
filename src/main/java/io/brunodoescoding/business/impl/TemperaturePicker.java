package io.brunodoescoding.business.impl;

import io.brunodoescoding.business.TemperatureStrategy;
import io.brunodoescoding.business.impl.temperature.BadRequestTemperatureImpl;
import io.brunodoescoding.business.impl.temperature.CityBasedTemperatureImpl;
import io.brunodoescoding.business.impl.temperature.CoordinatesBasedTemperatureImpl;
import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import io.brunodoescoding.service.TemperatureService;

import java.util.Arrays;
import java.util.List;

public final class TemperaturePicker {

    private static final List<TemperatureStrategy> strategies;

    static {
        strategies = Arrays.asList(new BadRequestTemperatureImpl(),
                                   new CoordinatesBasedTemperatureImpl(),
                                   new CityBasedTemperatureImpl());
    }

    public static TemperatureStrategy pick(PlaylistSuggestionDto data) {
        return strategies.stream().filter(strategy -> strategy.accepts(data)).findFirst().get();
    }

    public static void init(TemperatureService temperatureService) {
        strategies.stream().forEach(strategy -> strategy.setTemperatureService(temperatureService));
    }

}
