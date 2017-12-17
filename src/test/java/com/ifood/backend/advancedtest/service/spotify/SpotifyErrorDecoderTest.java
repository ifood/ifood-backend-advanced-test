package com.ifood.backend.advancedtest.service.spotify;

import com.ifood.backend.advancedtest.exception.ExpiredSessionException;
import feign.FeignException;
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
import static org.mockito.Mockito.*;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(Response.class)
public class SpotifyErrorDecoderTest {

    @Mock
    SpotifyApiAuthenticationService spotifyApiAuthenticationService;

    @InjectMocks
    SpotifyErrorDecoder spotifyErrorDecoder;

    @Mock
    Response response;

    @Mock
    Response.Body body;

    @Before
    public void setUp() throws Exception {
        when(response.status()).thenReturn(401);
        when(response.reason()).thenReturn("No reason at all");
        when(response.body()).thenReturn(body);
    }

    @Test
    public void shouldCallCacheRemoval() {
        try {
            spotifyErrorDecoder.decode("key", response);
        } catch (ExpiredSessionException e){}
        verify(spotifyApiAuthenticationService, times(1)).removeTokenFromCache();
    }

    @Test (expected = ExpiredSessionException.class)
    public void shouldThrowExceptionWhenHTTPCodeIs401() {
        spotifyErrorDecoder.decode("key", response);
    }

    @Test
    public void shouldReturnDefaultExceptionWhenHTTPCodeIsNot401() {
        when(response.status()).thenReturn(409);
        Exception exception = spotifyErrorDecoder.decode("key", response);
        assertNotNull(exception);
        assertTrue(exception instanceof FeignException);
    }

}