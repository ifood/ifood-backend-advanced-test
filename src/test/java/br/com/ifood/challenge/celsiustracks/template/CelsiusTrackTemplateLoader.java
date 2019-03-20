package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.CelsiusTrack;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Random;

public class CelsiusTrackTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        int quantity = new Random().nextInt(30) + 1;

        Fixture.of(CelsiusTrack.class).addTemplate(FixtureLabel.RANDOM_PLAYLIST.name(), new Rule(){{
            add("name", regex("\\w{10}"));
            add("tracks", has(quantity).of(CelsiusTrack.class, FixtureLabel.RANDOM_PLAYLIST.name()));
        }});
    }
}
