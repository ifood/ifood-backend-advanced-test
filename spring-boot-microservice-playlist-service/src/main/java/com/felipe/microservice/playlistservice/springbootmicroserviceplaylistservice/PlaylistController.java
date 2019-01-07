package com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.bo.PlaylistBO;
import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.exception.BusinessException;
import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist.GenreEnum;

/**
 * Service responsible for getting the playlist sound tracks given a genre
 * 
 * @author ffrazato
 */
@RestController
public class PlaylistController {
    @GetMapping("/soudtracknames/for/{genre}")
    public List<String> getPlayListSoundtracksByGenre(@PathVariable String genre) {

        List<String> playlistSoundTracks = null;

        try {
            PlaylistBO playlistBO = new PlaylistBO();
            playlistSoundTracks = playlistBO.getPlaylisSoundtrackNamesByGenre(GenreEnum.valueOf(genre));
        } catch (BusinessException be) {
            // TODO: Handle the error to retrieve something useful to client
        }

        return playlistSoundTracks;
    }
}
