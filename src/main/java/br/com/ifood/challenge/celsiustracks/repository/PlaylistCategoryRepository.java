package br.com.ifood.challenge.celsiustracks.repository;

import br.com.ifood.challenge.celsiustracks.entity.PlaylistCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistCategoryRepository extends JpaRepository<PlaylistCategoryEntity, Long> {
}
