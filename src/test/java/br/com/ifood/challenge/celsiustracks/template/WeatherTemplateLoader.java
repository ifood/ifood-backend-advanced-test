package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.openweathermap.Weather;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class WeatherTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Weather.class).addTemplate(FixtureLabel.RANDOM_WEATHER.name(), new Rule(){{
            add("temp", random(Double.class, 0d, 5d, 10d, 12d, 14d, 20d, 25d, 30d, 32d, 40d));
        }});
    }
}
