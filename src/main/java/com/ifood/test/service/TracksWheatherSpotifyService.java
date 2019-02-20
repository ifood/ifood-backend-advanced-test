package com.ifood.test.service;

import com.ifood.test.dto.Coord;

import java.util.List;

public interface TracksWheatherSpotifyService {
    List<String> getTracksByPlace( String place );
    List<String> getTracksByCord( Coord coord);

}
