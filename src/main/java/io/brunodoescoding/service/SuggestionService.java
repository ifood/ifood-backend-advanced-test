package io.brunodoescoding.service;

import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface SuggestionService {
    public List<String> pickSongs(PlaylistSuggestionDto params) throws HttpClientErrorException;
}
