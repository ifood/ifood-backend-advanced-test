package br.com.ifood.challenge.celsiustracks.domain.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @JsonProperty("href")
    private String href;
    @JsonProperty("icons")
    private List<Icon> icons = null;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}
