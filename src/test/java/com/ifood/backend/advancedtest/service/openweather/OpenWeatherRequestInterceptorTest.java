package com.ifood.backend.advancedtest.service.openweather;

import com.ifood.backend.advancedtest.service.openweather.interceptor.OpenWeatherRequestInterceptor;
import feign.RequestTemplate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RequestTemplate.class)
public class OpenWeatherRequestInterceptorTest {

    @Mock
    RequestTemplate requestTemplate;

    @InjectMocks
    OpenWeatherRequestInterceptor openWeatherRequestInterceptor;

    private String applicationId = "ASDFSDF454545FDS4545D4";

    @Before
    public void setup(){
        ReflectionTestUtils.setField(openWeatherRequestInterceptor, "applicationId", applicationId);
        when(requestTemplate.queries()).thenReturn(new HashMap<>());
        when(requestTemplate.body()).thenReturn("".getBytes());
    }

    @Test
    @Ignore
    public void shouldCallTemplateHeaderAtLeastOnce(){
        openWeatherRequestInterceptor.apply(requestTemplate);
        verify(requestTemplate, atLeastOnce()).queries(anyMap());
    }
}
