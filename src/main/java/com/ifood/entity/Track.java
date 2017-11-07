package com.ifood.entity;

/**
 * Represents a Track.
 */
public class Track {
    private final String name;

    public Track(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
