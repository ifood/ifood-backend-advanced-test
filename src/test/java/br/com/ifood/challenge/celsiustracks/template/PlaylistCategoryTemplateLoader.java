package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.celsiustracks.PlaylistCategory;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PlaylistCategoryTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(PlaylistCategory.class).addTemplate(FixtureLabel.RANDOM_CATEGORY.name(), new Rule(){{
            add("id", uniqueRandom(1l, 2l, 3l, 4l));
            add("name", uniqueRandom("party", "pop", "rock", "classical"));
        }});
    }
}
