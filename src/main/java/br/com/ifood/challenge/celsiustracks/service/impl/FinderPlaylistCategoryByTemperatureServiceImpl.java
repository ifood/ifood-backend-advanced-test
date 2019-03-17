package br.com.ifood.challenge.celsiustracks.service.impl;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import br.com.ifood.challenge.celsiustracks.entity.PlaylistCategoryByTemperatureRangeEntity;
import br.com.ifood.challenge.celsiustracks.entity.PlaylistCategoryEntity;
import br.com.ifood.challenge.celsiustracks.repository.PlaylistCategoryByTemperatureRangeRepository;
import br.com.ifood.challenge.celsiustracks.service.FinderPlaylistCategoryByTemperatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class FinderPlaylistCategoryByTemperatureServiceImpl implements FinderPlaylistCategoryByTemperatureService {

    private final PlaylistCategoryByTemperatureRangeRepository categoryByTemperatureRangeRepository;

    @Override
    public PlaylistCategory find(final Double temperature) {
        final PlaylistCategoryByTemperatureRangeEntity categoryByRange =
                categoryByTemperatureRangeRepository.findByStartTemperatureLessThanAndEndTemperatureGreaterThanEqual(temperature, temperature);

        final PlaylistCategoryEntity playlistCategoryEntity = ofNullable(categoryByRange)
                .map(PlaylistCategoryByTemperatureRangeEntity::getPlaylistCategoryEntity)
                .orElseThrow(EntityNotFoundException::new);

        return playlistCategoryEntity.toDomain();
    }

}
