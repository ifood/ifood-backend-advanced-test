package br.com.ariki.music.suggestion.by.weather.domain.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Playlist {
	
	@JsonProperty("name")
	private String description;
	private List<TrackInfo> trackInfo;

}
