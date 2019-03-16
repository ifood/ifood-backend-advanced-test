package br.com.ifood.challenge.celsiustracks.repository;

import br.com.ifood.challenge.celsiustracks.entity.PlaylistCategoryByTemperatureRangeEntity;
import br.com.ifood.challenge.celsiustracks.entity.PlaylistCategoryEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PlaylistCategoryByTemperatureRangeRepositoryTest {

    @Autowired
    private PlaylistCategoryRepository playlistCategoryRepository;

    @Autowired
    private PlaylistCategoryByTemperatureRangeRepository playlistCategoryByTemperatureRangeRepository;

    @Before
    public void setUp() {
        final PlaylistCategoryEntity party = new PlaylistCategoryEntity("party");
        final PlaylistCategoryEntity pop = new PlaylistCategoryEntity("pop");
        final PlaylistCategoryEntity rock = new PlaylistCategoryEntity("rock");
        final PlaylistCategoryEntity classical = new PlaylistCategoryEntity("classical");

        final List<PlaylistCategoryEntity> categories = Arrays.asList(party, pop, rock, classical);

        playlistCategoryRepository.saveAll(categories);

        final List<PlaylistCategoryByTemperatureRangeEntity> categoriesTemperaturesRange = Arrays.asList(
            new PlaylistCategoryByTemperatureRangeEntity(party, 30d, Double.POSITIVE_INFINITY),
            new PlaylistCategoryByTemperatureRangeEntity(pop, 14d, 30d),
            new PlaylistCategoryByTemperatureRangeEntity(rock, 10d, 14d),
            new PlaylistCategoryByTemperatureRangeEntity(classical, Double.NEGATIVE_INFINITY, 10d)
        );

        playlistCategoryByTemperatureRangeRepository.saveAll(categoriesTemperaturesRange);
    }

    @Test
    public void whenTemperatureMoreThan30ThenShouldFindPartyCategory() {
        checkCategoryFromTemperatures(Arrays.asList(30.1d, 31d, 42d, 43.1d, 50d), "party");
    }

    @Test
    public void whenTemperatureMoreThan14AndLessOrEqualTo30ThenShouldFindPopCategory() {
        checkCategoryFromTemperatures(Arrays.asList(14.1d, 20d, 21d, 25.6d, 30d), "pop");
    }

    @Test
    public void whenTemperatureMoreThan10AndLessOrEqualTo14ThenShouldFindRockCategory() {
        checkCategoryFromTemperatures(Arrays.asList(10.1d, 11.5d, 12d, 13.2d, 14d), "rock");
    }

    @Test
    public void whenTemperatureLessOrEqualTo10ThenShouldFindClassicalCategory() {
        checkCategoryFromTemperatures(Arrays.asList(-40d, -10d, 0d, 5.5d, 10d), "classical");
    }

    private void checkCategoryFromTemperatures(final List<Double> temperatures, final String expectedCategory) {
        temperatures.forEach(temperature -> {
            PlaylistCategoryByTemperatureRangeEntity categoryByTemperatureRange =
                    playlistCategoryByTemperatureRangeRepository.findByStartTemperatureLessThanAndEndTemperatureGreaterThanEqual(temperature, temperature);
            Assert.assertNotNull("It was not found a category for temperature " + temperature, categoryByTemperatureRange);
            Assert.assertEquals(expectedCategory, categoryByTemperatureRange.getPlaylistCategoryEntity().getName());
        });
    }

}