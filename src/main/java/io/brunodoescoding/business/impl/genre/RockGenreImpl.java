package io.brunodoescoding.business.impl.genre;

import io.brunodoescoding.business.GenreStrategy;

public class RockGenreImpl implements GenreStrategy {
    @Override
    public boolean accepts(double temperature) {
        return temperature >= 10.0 && temperature < 15;
    }

    @Override
    public String getGenre() {
        return "rock";
    }
}
