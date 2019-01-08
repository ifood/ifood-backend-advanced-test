package io.brunodoescoding.business.impl.genre;

import io.brunodoescoding.business.GenreStrategy;

public class PopGenreImpl implements GenreStrategy {
    @Override
    public boolean accepts(double temperature) {
        return temperature >= 15.0 && temperature <= 30.0;
    }

    @Override
    public String getGenre() {
        return "pop";
    }
}
