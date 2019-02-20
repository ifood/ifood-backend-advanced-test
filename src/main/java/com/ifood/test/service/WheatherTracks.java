package com.ifood.test.service;

import com.ifood.test.service.impl.SpotifyServiceImpl;

import java.util.List;

public enum WheatherTracks implements TracksByWheater {

    PARTY {
        @Override
        public List<String> getTracksByWhether() {
            SpotifyService spotifyService = new SpotifyServiceImpl();
            return spotifyService.getTrackNames("37i9dQZF1DX9KJJbsGEwfj");
        }
    }, POP {
        @Override
        public List<String> getTracksByWhether() {
            SpotifyService spotifyService = new SpotifyServiceImpl();
            return spotifyService.getTrackNames("37i9dQZF1DX4YhSbTs17ha");
        }
    }, ROCK {
        @Override
        public List<String> getTracksByWhether() {
            SpotifyService spotifyService = new SpotifyServiceImpl();
            return spotifyService.getTrackNames("37i9dQZF1DWYN0zdqzbEwl");
        }
    }, CLASSICAL {
        @Override
        public List<String> getTracksByWhether() {
            SpotifyService spotifyService = new SpotifyServiceImpl();
            return spotifyService.getTrackNames("37i9dQZF1DWV0gynK7G6pD");
        }
    };


}
