package br.com.ifood.challenge.celsiustracks.service;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinderPlaylistByCategory {
    List<CelsiusPlaylist> find(PlaylistCategory playlistCategory, final Pageable page);
}
