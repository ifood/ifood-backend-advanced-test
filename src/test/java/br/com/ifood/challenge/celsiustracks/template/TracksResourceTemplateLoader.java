package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.spotify.TrackItem;
import br.com.ifood.challenge.celsiustracks.domain.spotify.TracksResource;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Random;

public class TracksResourceTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(TracksResource.class).addTemplate(FixtureLabel.RANDOM_CATEGORY.name(), new Rule(){{
            add("items", has(getRandomQuantity()).of(TrackItem.class, FixtureLabel.RANDOM_CATEGORY.name()));
        }});
    }

    private Integer getRandomQuantity() {
        return new Random().nextInt(10) + 1;
    }
}
