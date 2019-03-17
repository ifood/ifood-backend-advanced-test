package br.com.ifood.challenge.celsiustracks.service;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import org.springframework.data.domain.Pageable;

public interface FinderPlaylistByCategory {
    CelsiusPlaylist find(PlaylistCategory playlistCategory, final Pageable page);
}
