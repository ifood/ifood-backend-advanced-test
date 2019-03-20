package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusPlaylist;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CelsiusPlaylistTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(CelsiusPlaylist.class).addTemplate(FixtureLabel.RANDOM_PLAYLIST.name(), new Rule(){{
            add("name", regex("\\w{30}"));
        }});
    }
}
