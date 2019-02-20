package com.ifood.test.service;

import com.ifood.test.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;



@RunWith(SpringRunner.class)
@SpringBootTest( classes = TestApplication.class)
@Slf4j
public class SpotifyServiceTest {

    @Autowired
    private SpotifyService service;

    private WheatherTracks wheatherTracks;


    @Test
    public void  getTrackNamesParty( ){
        final List<String> trackNames = service.getTrackNames("37i9dQZF1DX9KJJbsGEwfj");
        log.info(trackNames.toString());
        assertNotNull(trackNames);
    };

    @Test
    public void  getTrackNamesPop( ){
        final List<String> trackNames = service.getTrackNames("37i9dQZF1DX4YhSbTs17ha");
        log.info(trackNames.toString());
        assertNotNull(trackNames);
    };


    @Test
    public void  getTrackNamesRock( ){
        final List<String> trackNames = service.getTrackNames("37i9dQZF1DWYN0zdqzbEwl");
        log.info(trackNames.toString());
        assertNotNull(trackNames);
    };


    @Test
    public void  getTrackNamesClassical( ){
        final List<String> trackNames = service.getTrackNames("37i9dQZF1DWV0gynK7G6pD");
        log.info(trackNames.toString());
        assertNotNull(trackNames);
    };

    @Test
    public void  getTrackNamesEnumParty( ){
        final List<String> tracksByWhether = WheatherTracks.POP.getTracksByWhether();
        log.info(tracksByWhether.toString());
        assertNotNull(tracksByWhether);
    };

}
