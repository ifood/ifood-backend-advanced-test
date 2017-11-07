package com.ifood.api;

import com.ifood.Configuration;
import com.ifood.entity.Track;
import com.wrapper.spotify.exceptions.WebApiException;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
public class SpotifyAPITest {
    @Autowired
    private SpotifyAPI api;

    @Test public void
    readPartyTracks_tracksHavePartyInTheName() throws IOException, WebApiException {
        api.connect();

        Collection<Track> out = api.getTrackByCategory("party");

        Assert.assertFalse(out.size() == 0);
    }
}
