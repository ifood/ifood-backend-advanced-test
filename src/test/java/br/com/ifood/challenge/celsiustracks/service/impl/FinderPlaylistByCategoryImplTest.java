package br.com.ifood.challenge.celsiustracks.service.impl;

import br.com.ifood.challenge.celsiustracks.BaseUnitTest;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import br.com.ifood.challenge.celsiustracks.domain.spotify.PlaylistsResource;
import br.com.ifood.challenge.celsiustracks.domain.spotify.TracksResource;
import br.com.ifood.challenge.celsiustracks.exception.BusinessLogicException;
import br.com.ifood.challenge.celsiustracks.exception.PlaylistNotFoundException;
import br.com.ifood.challenge.celsiustracks.integration.spotify.SpotifyIntegrationService;
import br.com.ifood.challenge.celsiustracks.template.FixtureLabel;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class FinderPlaylistByCategoryImplTest extends BaseUnitTest {

    @InjectMocks
    private FinderPlaylistByCategoryImpl finderTracksByCategory;
    @Mock
    private SpotifyIntegrationService spotifyIntegrationService;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PlaylistCategory playlistCategory;

    private PageRequest page;

    private PlaylistsResource playlistResource;

    private PlaylistsResource invalidNumberOfPlaylistsResouce;

    private PlaylistsResource playlistResourceWithNoData;

    private TracksResource tracksResource;

    @Override
    public void setUp() {
        page = PageRequest.of(0, 1);
        playlistCategory = createObject(PlaylistCategory.class, FixtureLabel.RANDOM_CATEGORY);
        playlistResource = createObject(PlaylistsResource.class, FixtureLabel.RANDOM_CATEGORY);
        invalidNumberOfPlaylistsResouce = createObject(PlaylistsResource.class, FixtureLabel.INVALID_NUMBER_OF_PLAYLISTS);
        playlistResourceWithNoData = createObject(PlaylistsResource.class, FixtureLabel.WITH_NO_DATA);
        tracksResource = createObject(TracksResource.class, FixtureLabel.RANDOM_CATEGORY);
    }

    @Test
    public void shouldFindTracksByCategory() {
        when(spotifyIntegrationService.getPlaylistsByCategory(eq(playlistCategory.getName()), anyLong(), anyInt()))
                .thenReturn(playlistResource);
        when(spotifyIntegrationService.getTracksByPlaylist(anyString(), anyLong(), anyInt()))
                .thenReturn(tracksResource);
        final CelsiusPlaylist celsiusPlaylist = finderTracksByCategory.find(playlistCategory, page);
        assertNotNull(celsiusPlaylist);
    }

    @Test(expected = PlaylistNotFoundException.class)
    public void shouldFailWhenAnyPlaylistIsNotFound() {
        when(spotifyIntegrationService.getPlaylistsByCategory(eq(playlistCategory.getName()), anyLong(), anyInt()))
                .thenReturn(playlistResourceWithNoData);
        finderTracksByCategory.find(playlistCategory, page);
    }

    @Test
    public void shouldFailWhenTheNumberOfPlaylistIsInvalid() {
        exception.expect(BusinessLogicException.class);
        final Integer size = invalidNumberOfPlaylistsResouce.getPlaylists().getItems().size();
        final String invalidNumberOfPlayslistsMessage = "Invalid number of playlist found: " + size;
        exception.expectMessage(invalidNumberOfPlayslistsMessage);
        when(spotifyIntegrationService.getPlaylistsByCategory(eq(playlistCategory.getName()), anyLong(), anyInt()))
                .thenReturn(invalidNumberOfPlaylistsResouce);

        finderTracksByCategory.find(playlistCategory, page);
        verify(spotifyIntegrationService, never()).getTracksByPlaylist(anyString(), anyLong(), anyInt());
    }

    //TODO complementar testes
}