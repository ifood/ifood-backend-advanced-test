package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.spotify.PlaylistItem;
import br.com.ifood.challenge.celsiustracks.domain.spotify.Playlists;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Random;

public class PlaylistsTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Playlists.class).addTemplate(FixtureLabel.RANDOM_CATEGORY.name(), new Rule(){{
            add("items", has(1).of(PlaylistItem.class, FixtureLabel.RANDOM_CATEGORY.name()));
            add("total", getRandomTotal());
        }}).addTemplate(FixtureLabel.INVALID_NUMBER_OF_PLAYLISTS.name(), new Rule(){{
            add("items", has(2).of(PlaylistItem.class, FixtureLabel.RANDOM_CATEGORY.name()));
            add("total", getRandomTotal());
        }});
    }

    public Integer getRandomTotal() {
        return new Random().nextInt(100) + 1;
    }
}
