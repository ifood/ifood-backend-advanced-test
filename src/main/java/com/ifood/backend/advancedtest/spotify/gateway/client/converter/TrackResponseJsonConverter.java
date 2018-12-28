package com.ifood.backend.advancedtest.spotify.gateway.client.converter;

import com.ifood.backend.advancedtest.spotify.domain.Track;
import com.ifood.backend.advancedtest.spotify.gateway.client.json.TrackResponseJson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackResponseJsonConverter implements Converter<TrackResponseJson, List<Track>> {

    @Override
    public List<Track> convert(TrackResponseJson trackResponseJson) {

        return trackResponseJson.getItems().stream()
                .map(trackItemJson -> Track.builder()
                                        .name(trackItemJson.getTrack().getName())
                        .build()).collect(Collectors.toList());
    }
}
