package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.spotify.Track;
import br.com.ifood.challenge.celsiustracks.domain.spotify.TrackItem;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TrackItemTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(TrackItem.class).addTemplate(FixtureLabel.RANDOM_CATEGORY.name(), new Rule(){{
            add("track", one(Track.class, FixtureLabel.RANDOM_CATEGORY.name()));
        }});
    }
}
