package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.spotify.Playlists;
import br.com.ifood.challenge.celsiustracks.domain.spotify.PlaylistsResource;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PlaylistsResourceTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(PlaylistsResource.class).addTemplate(FixtureLabel.RANDOM_CATEGORY.name(), new Rule(){{
            add("playlists", one(Playlists.class, FixtureLabel.RANDOM_CATEGORY.name()));
        }}).addTemplate(FixtureLabel.INVALID_NUMBER_OF_PLAYLISTS.name(), new Rule(){{
            add("playlists", one(Playlists.class, FixtureLabel.INVALID_NUMBER_OF_PLAYLISTS.name()));
        }}).addTemplate(FixtureLabel.WITH_NO_DATA.name(), new Rule(){{
            add("playlists", null);
        }});
    }
}
