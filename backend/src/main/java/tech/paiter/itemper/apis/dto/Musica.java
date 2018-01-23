package tech.paiter.itemper.apis.dto;

import com.google.common.base.Strings;
import lombok.Data;

@Data
public class Musica {

    private String id;
    private String nome;
    private String href;
    private String openHref;

    public void constroiOpenHref() {
        if (!Strings.isNullOrEmpty(id)) {
            openHref = String.format("https://open.spotify.com/track/%s", id);
        }
    }
}
