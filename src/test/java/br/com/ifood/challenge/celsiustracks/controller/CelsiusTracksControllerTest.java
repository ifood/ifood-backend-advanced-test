package br.com.ifood.challenge.celsiustracks.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class CelsiusTracksControllerTest {

    @InjectMocks
    private CelsiusTracksController celsiusTracksController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(celsiusTracksController).build();
    }

    @Test
    public void getTracksByCity() {
        //TODO
    }

    //TODO nao esquecer dos casos alternativos/erros

    @Test
    public void getTracksByCoordinates() {
        //TODO
    }

    //TODO nao esquecer dos casos alternativos/erros
}