package com.ifood.backend.advancedtest.service.spotify;

import feign.RequestTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RequestTemplate.class)
public class SpotifyAuthenticationRequestInterceptorTest {

    @Mock
    RequestTemplate requestTemplate;

    @InjectMocks
    SpotifyAuthenticationRequestInterceptor spotifyAuthenticationRequestInterceptor;

    private String authenticationEndpoint = "/api/token";

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(spotifyAuthenticationRequestInterceptor, "authenticationEndpoint", authenticationEndpoint);
        when(requestTemplate.url()).thenReturn(authenticationEndpoint);

    }

    @Test
    public void shouldCallTemplateHeaderAtLeastOnce(){
        spotifyAuthenticationRequestInterceptor.apply(requestTemplate);
        verify(requestTemplate, atLeastOnce()).header(anyString(), anyString());
    }

}