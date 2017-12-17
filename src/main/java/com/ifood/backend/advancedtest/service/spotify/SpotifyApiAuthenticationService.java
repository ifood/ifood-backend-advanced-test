package com.ifood.backend.advancedtest.service.spotify;

import com.ifood.backend.advancedtest.service.spotify.client.SpotifyAuthApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpotifyApiAuthenticationService {

    public static final String CACHE_KEY = "spotifyAuthToken";
    public static final String GRANT_TYPE = "client_credentials";
    public static final String ACCESS_TOKEN_ATTRIBUTE = "access_token";

    @Autowired
    SpotifyAuthApiClient spotifyAuthApiClient;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(cacheNames = CACHE_KEY, key = "#root.target.CACHE_KEY")
    public String getSpotifyAuthToken() {
        final Map<String, String> response = spotifyAuthApiClient.authorizeUsingPOST(GRANT_TYPE);
        return response.get(ACCESS_TOKEN_ATTRIBUTE);
    }

    public void removeTokenFromCache() {
        final Cache cache = cacheManager.getCache(CACHE_KEY);
        cache.evict(CACHE_KEY);
    }
}
