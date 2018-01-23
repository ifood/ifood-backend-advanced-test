package tech.paiter.itemper.apis.dto;

import lombok.Data;

@Data
public class Playlist {

    private String id;
    private String name;
    private String hrefTrack;
    private Integer qtdTrack;

}
