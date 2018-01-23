package tech.paiter.itemper.models;

import lombok.Data;

@Data
public class Coordenadas {

    public Coordenadas(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private Double latitude;
    private Double longitude;

}
