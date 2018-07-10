package ifood.controller;

import ifood.config.MvcTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class HealthControllerTest extends MvcTest {

    @Test
    public void testHealthCheckShouldReturnOk() throws Exception {
        mvc.perform(get("/ping"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, containsString("plain/text")))
                .andExpect(content().string("pong"));
    }

}
