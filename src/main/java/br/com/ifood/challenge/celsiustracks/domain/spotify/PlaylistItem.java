package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
//        "collaborative",
//        "external_urls",
        "href",
        "id",
//        "images",
        "name",
        "owner",
//        "public",
        "snapshot_id",
        "tracks",
        "type",
        "uri"
})
@Data
public class PlaylistItem {

//    @JsonProperty("collaborative")
//    public Boolean collaborative;
//    @JsonProperty("external_urls")
//    public ExternalUrls externalUrls;
    @JsonProperty("href")
    public String href;
    @JsonProperty("id")
    public String id;
//    @JsonProperty("images")
//    public List<Image> images = null;
    @JsonProperty("name")
    public String name;
    @JsonProperty("owner")
    public Owner owner;
//    @JsonProperty("public")
//    public Object _public;
    @JsonProperty("snapshot_id")
    public String snapshotId;
    @JsonProperty("tracks")
    public Tracks tracks;
    @JsonProperty("type")
    public String type;
    @JsonProperty("uri")
    public String uri;

}
