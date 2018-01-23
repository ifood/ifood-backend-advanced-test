package tech.paiter.itemper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.paiter.itemper.apis.SpotifyApis;
import tech.paiter.itemper.apis.SpotifyUtil;
import tech.paiter.itemper.services.utils.categorias.*;

import java.io.IOException;

@Service
public class SpotifyService {

    @Autowired
    SpotifyApis spotifyApis;

    public void getPlaylistByTemp(Double temperatura) throws IOException {

        ICategoria party = new PartyCategoria();
        ICategoria rock = new RockCategoria();
        ICategoria pop = new PopCategoria();
        ICategoria classica = new ClassicaCategoria();

        party.setNextChain(rock);
        rock.setNextChain(pop);
        pop.setNextChain(classica);


        spotifyApis.getPlaylistByCategoria(party.dispense(temperatura));
    }
}
