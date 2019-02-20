package com.ifood.test.service;

import com.ifood.test.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest( classes = TestApplication.class)
@Slf4j
public class TracksWheatherSpotifyServiceTest {
    @Autowired
    TracksWheatherSpotifyService wheatherSpotifyService;

    @Test
    public void getTracksByPlace(){
        final List<String> almoloyaWeather = wheatherSpotifyService.getTracksByPlace("Almoloya");
        log.info(almoloyaWeather.toString());
        assertNotNull(almoloyaWeather);

    }

}
