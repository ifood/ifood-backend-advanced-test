package com.ifood.backend.advancedtest.service.openweather;

import com.ifood.backend.advancedtest.exception.PlaylistClientException;
import com.ifood.backend.advancedtest.exception.PlaylistServerException;
import feign.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(Response.class)
public class OpenWeatherErrorDecoderTest {

    @InjectMocks
    OpenWeatherErrorDecoder errorDecoder;

    @Mock
    Response response;

    @Mock
    Response.Body body;

    @Before
    public void setup(){
        when(response.status()).thenReturn(401);
        when(response.reason()).thenReturn("No reason at all");
        when(response.body()).thenReturn(body);
    }

    @Test
    public void shouldReturnClientExceptionWhenHTTPCodeIs400() {
        when(response.status()).thenReturn(400);
        Exception exception = errorDecoder.decode("key", response);
        assertNotNull(exception);
        assertTrue(exception instanceof PlaylistClientException);
    }

    @Test
    public void shouldReturnServerExceptionWhenHTTPCodeIs500() {
        when(response.status()).thenReturn(500);
        Exception exception = errorDecoder.decode("key", response);
        assertNotNull(exception);
        assertTrue(exception instanceof PlaylistServerException);
    }

}
