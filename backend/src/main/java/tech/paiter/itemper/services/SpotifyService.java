package tech.paiter.itemper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.paiter.itemper.apis.SpotifyApis;
import tech.paiter.itemper.apis.dto.Musica;
import tech.paiter.itemper.services.utils.chains.*;

import java.io.IOException;
import java.util.List;

@Service
public class SpotifyService {

    @Autowired
    SpotifyApis spotifyApis;

    public List<Musica> getPlaylistByTemp(Double temperatura) throws IOException {
        return spotifyApis.getPlaylistByCategoria(encontraCategoria(temperatura));
    }

    private String encontraCategoria(Double temperatura) throws IOException {

        ICategoria party = new PartyCategoria();
        ICategoria rock = new RockCategoria();
        ICategoria pop = new PopCategoria();
        ICategoria classica = new ClassicaCategoria();

        party.setNextChain(rock);
        rock.setNextChain(pop);
        pop.setNextChain(classica);

        return party.dispense(temperatura);
    }
}
