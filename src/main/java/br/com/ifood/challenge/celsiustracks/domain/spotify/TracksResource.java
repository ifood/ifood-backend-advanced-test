package br.com.ifood.challenge.celsiustracks.domain.spotify;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "href",
        "items",
        "limit",
        "next",
        "offset",
        "previous",
        "total"
})
@Data
public class TracksResource {

    @JsonProperty("href")
    public String href;
    @JsonProperty("items")
    public List<TrackItem> items = null;
    @JsonProperty("limit")
    public Integer limit;
    @JsonProperty("next")
    public String next;
    @JsonProperty("offset")
    public Integer offset;
    @JsonProperty("previous")
    public String previous;
    @JsonProperty("total")
    public Integer total;

}
