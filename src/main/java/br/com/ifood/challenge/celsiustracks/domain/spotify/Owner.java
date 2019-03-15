package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "external_urls",
        "href",
        "id",
        "type",
        "uri"
})
@Data
public class Owner {

//    @JsonProperty("external_urls")
//    public ExternalUrls_ externalUrls;
    @JsonProperty("href")
    public String href;
    @JsonProperty("id")
    public String id;
    @JsonProperty("type")
    public String type;
    @JsonProperty("uri")
    public String uri;

}
