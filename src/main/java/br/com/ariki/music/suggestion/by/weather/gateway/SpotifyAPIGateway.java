package br.com.ariki.music.suggestion.by.weather.gateway;

import br.com.ariki.music.suggestion.by.weather.domain.entity.MusicStyle;
import br.com.ariki.music.suggestion.by.weather.domain.entity.Playlist;

public interface SpotifyAPIGateway {
	
	Playlist getPlaylist(MusicStyle musicStyle, String token);
}
