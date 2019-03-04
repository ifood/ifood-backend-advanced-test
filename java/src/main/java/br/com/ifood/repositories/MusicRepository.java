package br.com.ifood.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifood.models.MusicModel;

@Repository
public interface MusicRepository extends JpaRepository<MusicModel, Long> {

}
