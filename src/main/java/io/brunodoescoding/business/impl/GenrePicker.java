package io.brunodoescoding.business.impl;

import io.brunodoescoding.business.GenreStrategy;
import io.brunodoescoding.business.impl.genre.ClassicalGenreImpl;
import io.brunodoescoding.business.impl.genre.PartyGenreImpl;
import io.brunodoescoding.business.impl.genre.PopGenreImpl;
import io.brunodoescoding.business.impl.genre.RockGenreImpl;

import java.util.Arrays;
import java.util.List;

public final class GenrePicker {
    private static final List<GenreStrategy> genres;

    static {
        genres = Arrays.asList(new PartyGenreImpl(), new PopGenreImpl(), new RockGenreImpl(), new ClassicalGenreImpl());
    }

    public static GenreStrategy pick(double temperature) {
        return genres.stream().filter(genre -> genre.accepts(temperature)).findFirst().get();
    }
}
