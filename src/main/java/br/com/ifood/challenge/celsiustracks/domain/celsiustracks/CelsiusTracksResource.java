package br.com.ifood.challenge.celsiustracks.domain.celsiustracks;

import lombok.Getter;

@Getter
public class CelsiusTracksResource {
    private CelsiusPlaylist playlist;

    public CelsiusTracksResource(final CelsiusPlaylist playlist) {
        this.playlist = playlist;
    }
}
