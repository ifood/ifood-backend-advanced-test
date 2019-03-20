package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.spotify.PlaylistItem;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PlaylistItemTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(PlaylistItem.class).addTemplate(FixtureLabel.RANDOM_CATEGORY.name(), new Rule(){{
            add("id", regex("\\w{10}"));
            add("name", regex("\\w{10}"));
            add("type", random("party", "pop", "rock", "classical"));
        }});
    }
}
