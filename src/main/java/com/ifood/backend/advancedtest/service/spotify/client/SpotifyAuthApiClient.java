package com.ifood.backend.advancedtest.service.spotify.client;

import com.ifood.backend.advancedtest.util.ApiClient;
import com.ifood.backend.configuration.SpotifyServiceConfiguration;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.Map;

@FunctionalInterface
@FeignClient(name = "services.spotify", url = "${services.spotify.token.uri}", configuration = SpotifyServiceConfiguration.class)
public interface SpotifyAuthApiClient extends ApiClient.Api {

    @RequestLine("POST /api/token")
    @Headers({"Accept: */*", "Cache-Control: no-cache",
            "Content-Type: application/x-www-form-urlencoded"})
    Map<String, String> authorizeUsingPOST(@Param("grant_type") String grantType);

}
