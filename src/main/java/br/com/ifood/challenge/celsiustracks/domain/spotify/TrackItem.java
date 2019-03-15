package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "added_at",
        "added_by",
        "is_local",
        "primary_color",
        "track",
        "video_thumbnail"
})
public class TrackItem {

//    @JsonProperty("added_at")
//    public String addedAt;
//    @JsonProperty("added_by")
//    public AddedBy addedBy;
//    @JsonProperty("is_local")
//    public Boolean isLocal;
//    @JsonProperty("primary_color")
//    public Object primaryColor;
    @JsonProperty("track")
    public Track track;
//    @JsonProperty("video_thumbnail")
//    public VideoThumbnail videoThumbnail;

}
