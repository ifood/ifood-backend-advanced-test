package br.com.ifood.challenge.celsiustracks.domain.spotify;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "album",
        "artists",
        "available_markets",
        "disc_number",
        "duration_ms",
        "episode",
        "explicit",
        "external_ids",
        "external_urls",
        "href",
        "id",
        "is_local",
        "name",
        "popularity",
        "preview_url",
        "track",
        "track_number",
        "type",
        "uri"
})
@Data
public class Track {

    @JsonProperty("album")
    public Album album;
    @JsonProperty("artists")
    public List<Artist> artists = null;
//    @JsonProperty("available_markets")
//    public List<Object> availableMarkets = null;
//    @JsonProperty("disc_number")
//    public Integer discNumber;
//    @JsonProperty("duration_ms")
//    public Integer durationMs;
//    @JsonProperty("episode")
//    public Boolean episode;
//    @JsonProperty("explicit")
//    public Boolean explicit;
//    @JsonProperty("external_ids")
//    public ExternalIds externalIds;
//    @JsonProperty("external_urls")
//    public ExternalUrls____ externalUrls;
    @JsonProperty("href")
    public String href;
    @JsonProperty("id")
    public String id;
//    @JsonProperty("is_local")
//    public Boolean isLocal;
    @JsonProperty("name")
    public String name;
    @JsonProperty("popularity")
    public Integer popularity;
    @JsonProperty("preview_url")
    public String previewUrl;
    @JsonProperty("track")
    public Boolean track;
    @JsonProperty("track_number")
    public Integer trackNumber;
    @JsonProperty("type")
    public String type;
    @JsonProperty("uri")
    public String uri;

}
