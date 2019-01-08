package io.brunodoescoding.service.impl;

import io.brunodoescoding.business.GenreStrategy;
import io.brunodoescoding.business.impl.GenrePicker;
import io.brunodoescoding.business.impl.TemperaturePicker;
import io.brunodoescoding.dto.track.PlaylistSuggestionDto;
import io.brunodoescoding.service.MusicPlatformService;
import io.brunodoescoding.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final MusicPlatformService musicPlatformService;

    @Autowired
    public SuggestionServiceImpl(MusicPlatformService musicPlatformService) {
        this.musicPlatformService = musicPlatformService;
    }

    @Override
    public List<String> pickSongs(PlaylistSuggestionDto params) throws HttpClientErrorException {
        double temperature = TemperaturePicker.pick(params).retrieve(params);

        GenreStrategy found = GenrePicker.pick(temperature);
        String genre = found.getGenre();

        return musicPlatformService.pickSongs(genre);
    }

}
