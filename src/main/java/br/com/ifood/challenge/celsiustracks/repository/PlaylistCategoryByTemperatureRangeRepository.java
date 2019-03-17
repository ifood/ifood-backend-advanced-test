package br.com.ifood.challenge.celsiustracks.repository;

import br.com.ifood.challenge.celsiustracks.entity.PlaylistCategoryByTemperatureRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistCategoryByTemperatureRangeRepository extends JpaRepository<PlaylistCategoryByTemperatureRangeEntity, Long> {

    //TODO customizar o metodo para nao receber 2 parametros
    PlaylistCategoryByTemperatureRangeEntity findByStartTemperatureLessThanAndEndTemperatureGreaterThanEqual(Double temperatureGreateThanEqual, Double temperatureLessThanEqual);

}
