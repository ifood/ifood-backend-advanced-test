package ifood.config;

import ifood.utils.WiremockStarter;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public abstract class MvcTest extends BaseTest {

    @Autowired
    protected MockMvc mvc;

    @Before
    public void wireMockSetUp() {
        WiremockStarter.getInstance().startOnce();
    }
}
