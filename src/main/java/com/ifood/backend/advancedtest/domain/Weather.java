package com.ifood.backend.advancedtest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    private int id;
    private String name;
    private int cod;
    private long dt;
    private MainInfo main;
    private Coordinate coord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", dt=" + dt +
                ", main=" + main +
                ", coord=" + coord +
                '}';
    }

    public class MainInfo {
        public int id;
        public float temp;
        public float pressure;

        @JsonProperty("temp_min")
        public float tempMin;

        @JsonProperty("temp_max")
        public float tempMax;

        @Override
        public String toString() {
            return "MainInfo{" +
                    "id=" + id +
                    ", temp=" + temp +
                    ", pressure=" + pressure +
                    ", tempMin=" + tempMin +
                    ", tempMax=" + tempMax +
                    '}';
        }
    }
}
