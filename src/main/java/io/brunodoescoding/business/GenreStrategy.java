package io.brunodoescoding.business;

public interface GenreStrategy {
    public boolean accepts(double temperature);
    public String getGenre();
}
