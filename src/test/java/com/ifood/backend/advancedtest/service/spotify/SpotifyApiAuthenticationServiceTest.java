package com.ifood.backend.advancedtest.service.spotify;

import com.ifood.backend.advancedtest.service.spotify.client.SpotifyAuthApiClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class SpotifyApiAuthenticationServiceTest {

    @Mock
    CacheManager cacheManager;

    @Mock
    SpotifyAuthApiClient authApiClient;

    @InjectMocks
    SpotifyApiAuthenticationService spotifyApiAuthenticationService;

    @Mock
    Cache cache;

    String token = "#@idToken@#";

    @Before
    public void setUp() throws Exception {
        when(authApiClient.authorizeUsingPOST(SpotifyApiAuthenticationService.GRANT_TYPE))
                .thenReturn(mockAuthenticateRespnse());

        when(cacheManager.getCache(anyString())).thenReturn(cache);
    }

    @Test
    public void shouldCallAuthenticateAPIWithBrand() {
        String tokenReturned = spotifyApiAuthenticationService.getSpotifyAuthToken();
        Assert.assertNotNull(token);
        Assert.assertEquals(token, tokenReturned);
    }

    @Test
    public void shouldRemoveFromCache() {
        spotifyApiAuthenticationService.removeTokenFromCache();
        verify(cacheManager, times(1)).getCache(SpotifyApiAuthenticationService.CACHE_KEY);
        verify(cache, times(1)).evict(SpotifyApiAuthenticationService.CACHE_KEY);
    }

    private Map<String, String> mockAuthenticateRespnse() {
        Map<String, String> map = new HashMap<>();
        map.put("access_token", token);
        return map;
    }
}