package br.com.ifood.challenge.celsiustracks;

import br.com.ifood.challenge.celsiustracks.template.FixtureLabel;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseUnitTest {

    private static final String TEMPLATE_LOADER_BASE_DIR = "br.com.ifood.challenge.celsiustracks.template";

    @BeforeClass
    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates(TEMPLATE_LOADER_BASE_DIR);
    }

    @Before
    public abstract void setUp();

    protected <T> T createObject(final Class<?> clazz, final FixtureLabel label) {
        return Fixture.from(clazz).gimme(label.name());
    }

}
