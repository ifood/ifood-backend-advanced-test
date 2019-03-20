package br.com.ifood.challenge.celsiustracks.template;

import br.com.ifood.challenge.celsiustracks.domain.openweathermap.Weather;
import br.com.ifood.challenge.celsiustracks.domain.openweathermap.WeatherResource;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class WeatherResourceTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(WeatherResource.class).addTemplate(FixtureLabel.RANDOM_WEATHER.name(), new Rule(){{
            add("weather", one(Weather.class, FixtureLabel.RANDOM_WEATHER.name()));
        }});
    }
}
