package com.ifood.test.service;

import com.ifood.test.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = TestApplication.class)
@Slf4j
public class TracksWheatherSpotifyTest {

    @Autowired
    private TracksWheatherSpotifyService tracksWheatherSpotify;

}
