package io.brunodoescoding.business.impl.genre;

import io.brunodoescoding.business.GenreStrategy;

public class PartyGenreImpl implements GenreStrategy {
    @Override
    public boolean accepts(double temperature) {
        return temperature > 30.0;
    }

    @Override
    public String getGenre() {
        return "party";
    }
}
