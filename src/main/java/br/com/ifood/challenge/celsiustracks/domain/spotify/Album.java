package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "album_type",
        "artists",
        "available_markets",
        "external_urls",
        "href",
        "id",
        "images",
        "name",
        "release_date",
        "release_date_precision",
        "total_tracks",
        "type",
        "uri"
})
public class Album {

    @JsonProperty("album_type")
    public String albumType;
    @JsonProperty("artists")
    public List<Artist> artists = null;
//    @JsonProperty("available_markets")
//    public List<Object> availableMarkets = null;
//    @JsonProperty("external_urls")
//    public ExternalUrls__ externalUrls;
    @JsonProperty("href")
    public String href;
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
