package br.com.ifood.challenge.celsiustracks.service.impl;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import br.com.ifood.challenge.celsiustracks.entity.PlaylistCategoryByTemperatureRangeEntity;
import br.com.ifood.challenge.celsiustracks.entity.PlaylistCategoryEntity;
import br.com.ifood.challenge.celsiustracks.repository.PlaylistCategoryByTemperatureRangeRepository;
import br.com.ifood.challenge.celsiustracks.service.FinderPlaylistCategoryByTemperatureService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinderPlaylistCategoryByTemperatureServiceImpl implements FinderPlaylistCategoryByTemperatureService {

    private final PlaylistCategoryByTemperatureRangeRepository categoryByTemperatureRangeRepository;

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackPlaylistCategory")
    public PlaylistCategory find(final Double temperature) {
        final PlaylistCategoryByTemperatureRangeEntity categoryByRange =
                categoryByTemperatureRangeRepository.findByStartTemperatureLessThanAndEndTemperatureGreaterThanEqual(temperature, temperature);

        final PlaylistCategoryEntity playlistCategoryEntity = ofNullable(categoryByRange)
                .map(PlaylistCategoryByTemperatureRangeEntity::getPlaylistCategoryEntity)
                .orElseThrow(EntityNotFoundException::new);

        return playlistCategoryEntity.toDomain();
    }

    //TODO improve fallback
    public PlaylistCategory getFallbackPlaylistCategory(final Double temperature) {
        log.warn("Fallback for PlaylistCategory");

        final List<String> categories = Arrays.asList("party", "pop", "rock", "classical");
        final int randomIndex = new Random().nextInt(categories.size());
        return new PlaylistCategory(0l, categories.get(randomIndex));
    }

}
