package br.com.ifood.challenge.celsiustracks.service;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;

public interface FinderPlaylistCategoryByTemperatureService {
    PlaylistCategory find(Double weather);
}
