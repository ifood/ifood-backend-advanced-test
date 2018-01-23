package tech.paiter.itemper.apis.builders;

import tech.paiter.itemper.apis.dto.Musica;

public class MusicaBuilder {

    private Musica musica;

    public MusicaBuilder() {
        musica = new Musica();
    }

    public MusicaBuilder setId(String id){
        musica.setId(id);
        return this;
    }

    public MusicaBuilder setNome(String nome) {
        musica.setNome(nome);
        return this;
    }

    public MusicaBuilder setHref(String href) {
        musica.setHref(href);
        return this;
    }

    public MusicaBuilder constroiOpenHref(){
        musica.constroiOpenHref();
        return this;
    }

    public Musica build(){
        return musica;
    }
}
