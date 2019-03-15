package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "height",
        "url",
        "width"
})
public class Icon {
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("url")
    private String url;
    @JsonProperty("width")
    private Integer width;
}
