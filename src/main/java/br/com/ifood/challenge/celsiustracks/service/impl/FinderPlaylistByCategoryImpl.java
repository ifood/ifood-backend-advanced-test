package br.com.ifood.challenge.celsiustracks.service.impl;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusTrack;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import br.com.ifood.challenge.celsiustracks.domain.spotify.PlaylistItem;
import br.com.ifood.challenge.celsiustracks.domain.spotify.PlaylistsResource;
import br.com.ifood.challenge.celsiustracks.domain.spotify.TrackItem;
import br.com.ifood.challenge.celsiustracks.domain.spotify.TracksResource;
import br.com.ifood.challenge.celsiustracks.exception.BusinessLogicException;
import br.com.ifood.challenge.celsiustracks.exception.PlaylistNotFoundException;
import br.com.ifood.challenge.celsiustracks.integration.spotify.SpotifyIntegrationService;
import br.com.ifood.challenge.celsiustracks.service.FinderPlaylistByCategory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinderPlaylistByCategoryImpl implements FinderPlaylistByCategory {
    private static final long OFFSET = 0l;
    private static final int LIMIT = 1;

    private final SpotifyIntegrationService spotifyIntegrationService;

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackCelsiusPlaylist")
    public CelsiusPlaylist find(final PlaylistCategory playlistCategory, final Pageable page) {
        final Integer numberOfPlaylists = getMaxNumberOfPlaylistByCategory(playlistCategory);
        final long offset = new Random().nextInt(numberOfPlaylists);

        final PlaylistsResource playlistByCategory = spotifyIntegrationService.getPlaylistsByCategory(playlistCategory.getName(), offset, LIMIT);

        final List<PlaylistItem> playlists = playlistByCategory.getPlaylists().getItems();
        if (playlists.size() != LIMIT) {
            throw new BusinessLogicException("Invalid number of playlist found: " + playlists.size());
        }

        final PlaylistItem playlistItem = playlists.get(0);
        final List<CelsiusTrack> celsiusTracks = findAndConvertToCelsiusPlaylist(playlistItem.getId(), page);

        return new CelsiusPlaylist(playlistItem.getName(), celsiusTracks);
    }

    private Integer getMaxNumberOfPlaylistByCategory(final PlaylistCategory playlistCategory) {
        final PlaylistsResource playlistsByCategory =
                spotifyIntegrationService.getPlaylistsByCategory(playlistCategory.getName(), OFFSET, LIMIT);

        final Integer maxNumberOfPlaylist =
                ofNullable(playlistsByCategory).map(PlaylistsResource::getTotalPlaylists).orElse(0);

        if (maxNumberOfPlaylist == 0) {
            throw new PlaylistNotFoundException();
        }

        return maxNumberOfPlaylist;
    }

    private List<CelsiusTrack> findAndConvertToCelsiusPlaylist(final String playlistId, final Pageable page) {
        final TracksResource tracksResource = spotifyIntegrationService.getTracksByPlaylist(playlistId, page.getOffset(), page.getPageSize());

        return tracksResource.getItems().stream()
                .map(TrackItem::getTrackName)
                .map(CelsiusTrack::new)
                .collect(toList());
    }

    public CelsiusPlaylist getFallbackCelsiusPlaylist(final PlaylistCategory playlistCategory, final Pageable page) {
        log.warn("Fallback getFallbackCelsiusPlaylist called");
        //TODO poderia ter uma notificacao para o newRelic para monitorar o numero de ocorrencias de fallback

        final List<CelsiusTrack> tracks = Arrays.asList(
                new CelsiusTrack("Tiririca's track"),
                new CelsiusTrack("Falcao's track"),
                new CelsiusTrack("ET e Rodolfo's track"),
                new CelsiusTrack("Nissim Ourfali's track"),
                new CelsiusTrack("Akundum – Emaconhada's track"),
                new CelsiusTrack("Virguloides – Bagulho no Bumba's track"),
                new CelsiusTrack("Macarena's track"),
                new CelsiusTrack("PO Box's track"),
                new CelsiusTrack("Food's track")
        );
        final CelsiusPlaylist celsiusPlaylist = new CelsiusPlaylist("fallbackPlaylist", tracks);
        return celsiusPlaylist;
    }

}
