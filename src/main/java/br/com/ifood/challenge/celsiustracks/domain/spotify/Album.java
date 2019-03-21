package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    @JsonProperty("album_type")
    private String albumType;
    @JsonProperty("artists")
    private List<Artist> artists = null;
    @JsonProperty("href")
    private String href;
    @JsonProperty("id")
    public String id;
//    @JsonProperty("images")
//    public List<Image> images = null;
    @JsonProperty("name")
    public String name;
//    @JsonProperty("release_date")
//    public String releaseDate;
//    @JsonProperty("release_date_precision")
//    public String releaseDatePrecision;
    @JsonProperty("total_tracks")
    public Integer totalTracks;
    @JsonProperty("type")
    public String type;
    @JsonProperty("uri")
    public String uri;

}
