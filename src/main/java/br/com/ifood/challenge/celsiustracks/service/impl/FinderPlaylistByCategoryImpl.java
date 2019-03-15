package br.com.ifood.challenge.celsiustracks.service.impl;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import br.com.ifood.challenge.celsiustracks.integration.spotify.SpotifyIntegrationService;
import br.com.ifood.challenge.celsiustracks.service.FinderPlaylistByCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinderPlaylistByCategoryImpl implements FinderPlaylistByCategory {

    private final SpotifyIntegrationService spotifyIntegrationService;

    @Override
    public List<CelsiusPlaylist> find(final PlaylistCategory playlistCategory, final Pageable page) {
        spotifyIntegrationService.getPlaylistsByCategory(playlistCategory.getName(), page.getOffset(), page.getPageSize());
        return null; //TODO
    }

}
