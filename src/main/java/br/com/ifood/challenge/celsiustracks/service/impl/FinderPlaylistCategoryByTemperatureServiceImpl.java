package br.com.ifood.challenge.celsiustracks.service.impl;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import br.com.ifood.challenge.celsiustracks.service.FinderPlaylistCategoryByTemperatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinderPlaylistCategoryByTemperatureServiceImpl implements FinderPlaylistCategoryByTemperatureService {

    @Override
    public PlaylistCategory find(final Double weather) {
        return null; //TODO obter a categoria a partir do banco de dados
    }

}
