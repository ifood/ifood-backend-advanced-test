package ifood.controller;

import ifood.component.OpenWeather;
import ifood.config.MvcTest;
import ifood.model.OpenWeatherResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlaylistBuilderControllerTest { /*extends MvcTest {

    @MockBean
    private OpenWeather openWeather;

    @Test
    public void successCitynameMvcTest() throws Exception {

        when(openWeather.getCityTemp("campinas", null, null))
                .thenReturn(new OpenWeatherResponse("Campinas", 25.0, "BR"));

        final String expected = "{\"cityname\":\"Campinas\",\"temp\":25.0,\"country\":\"BR\"}";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/weather?cityname=campinas&appid=testkey&units=metric")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result ->
                        Assert.assertEquals(expected, result.getResponse().getContentAsString())
                );

        verify(openWeather, times(1)).getCityTemp(eq("campinas"), eq(null), eq(null));
    }

    @Test
    public void successGeoMvcTest() throws Exception {

        when(openWeather.getCityTemp(null, 10.0, 20.0))
                .thenReturn(new OpenWeatherResponse("Sorocaba", 15.0, "BR"));

        final String expected = "{\"cityname\":\"Sorocaba\",\"temp\":15.0,\"country\":\"BR\"}";

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/weather?lat=10&lon=20&appid=testkey&units=metric")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result ->
                        Assert.assertEquals(expected, result.getResponse().getContentAsString())
                );

        verify(openWeather, times(1)).getCityTemp(eq(null), eq(10.0), eq(20.0));
    }a*/
}
