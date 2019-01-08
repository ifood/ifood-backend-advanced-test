package io.brunodoescoding.business.impl.genre;

import io.brunodoescoding.business.GenreStrategy;

public class ClassicalGenreImpl implements GenreStrategy {
    @Override
    public boolean accepts(double temperature) {
        return temperature < 10.0;
    }

    @Override
    public String getGenre() {
        return "classical";
    }
}
