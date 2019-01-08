package io.brunodoescoding.service;

import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface MusicPlatformService {
    public List<String> pickSongs(String genre) throws HttpClientErrorException;
    public String generateToken() throws HttpClientErrorException;
}
