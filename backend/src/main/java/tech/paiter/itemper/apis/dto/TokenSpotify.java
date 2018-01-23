package tech.paiter.itemper.apis.dto;

import lombok.Data;

@Data
public class TokenSpotify {

    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String scope;

}
