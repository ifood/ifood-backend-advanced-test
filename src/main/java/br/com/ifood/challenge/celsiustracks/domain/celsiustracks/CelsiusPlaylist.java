package br.com.ifood.challenge.celsiustracks.domain.celsiustracks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CelsiusPlaylist {
    private String name;
    private List<CelsiusTrack> tracks = new ArrayList<>();
}
