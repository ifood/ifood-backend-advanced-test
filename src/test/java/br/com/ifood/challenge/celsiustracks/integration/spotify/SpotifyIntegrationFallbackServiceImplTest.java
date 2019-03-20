package br.com.ifood.challenge.celsiustracks.integration.spotify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyIntegrationFallbackServiceImplTest {

    @InjectMocks
    private SpotifyIntegrationFallbackServiceImpl spotifyIntegrationFallbackService;

    @Test
    public void shouldGetCategories() {
        assertNotNull(spotifyIntegrationFallbackService.getCategories(1l, 1));
    }

    @Test
    public void shouldGetPlaylistsByCategory() {
        assertNotNull(spotifyIntegrationFallbackService.getPlaylistsByCategory("party", 1l, 1));
    }

    @Test
    public void shouldGetTracksByPlaylist() {
        assertNotNull(spotifyIntegrationFallbackService.getTracksByPlaylist("1a2b4c5d6e", 1l, 1));
    }
}