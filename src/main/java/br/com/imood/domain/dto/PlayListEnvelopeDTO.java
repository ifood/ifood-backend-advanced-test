package br.com.imood.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PlayListEnvelopeDTO {

    @JsonProperty("playlists")
    private ItemsEnvelopeDTO<PlayListItemDTO> playlist;

}
