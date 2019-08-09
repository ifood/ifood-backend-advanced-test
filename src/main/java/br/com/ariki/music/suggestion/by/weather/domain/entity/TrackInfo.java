package br.com.ariki.music.suggestion.by.weather.domain.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TrackInfo {
	
	private String artistName;
	private String musicName;
	private String href;

}
