package br.com.imood.domain.dto;

import lombok.Getter;

@Getter
public class WeatherDTO {

    private WeatherMainDTO main;

    public Double getTemperature(){
        return main != null ? main.getTemperature() : null;
    }

}
