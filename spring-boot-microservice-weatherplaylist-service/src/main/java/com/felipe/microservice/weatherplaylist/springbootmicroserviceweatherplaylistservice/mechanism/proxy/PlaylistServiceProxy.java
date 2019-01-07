package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.fallbackfactory.PlaylistServiceProxyFallbackFactory;

/**
 * Feign client for sending HTTP to playlist service
 *
 * @author ffrazato
 */
@FeignClient(name = "playlist-service", fallbackFactory = PlaylistServiceProxyFallbackFactory.class)
@RibbonClient(name = "playlist-service")
public interface PlaylistServiceProxy {
    /**
     * Get soundtrack names by genre
     *
     * @param genre
     *            playlist genre
     * @return soundtrack names
     */
    @GetMapping("/soudtracknames/for/{genre}")
    public List<String> retrieveSoundtrackNamesByGenre(@PathVariable("genre") String genre);
}