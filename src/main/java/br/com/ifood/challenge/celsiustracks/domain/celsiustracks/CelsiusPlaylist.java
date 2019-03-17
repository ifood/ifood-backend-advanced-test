package br.com.ifood.challenge.celsiustracks.domain.celsiustracks;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CelsiusPlaylist {
    private String name;
    private List<CelsiusTrack> tracks = new ArrayList<>();
}
