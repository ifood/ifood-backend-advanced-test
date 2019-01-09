package br.com.imood.web.resource;

import br.com.imood.domain.dto.TrackInfoDTO;
import br.com.imood.error.service.ExternalServiceException;
import br.com.imood.error.InvalidGenreException;
import br.com.imood.web.service.MoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mood", produces = MediaType.APPLICATION_JSON_VALUE)
public class MoodResource {

    private final Logger logger = LoggerFactory.getLogger(MoodResource.class);

    private MoodService moodService;

    public MoodResource(MoodService moodService){
        this.moodService = moodService;
    }

    /**
     * Returns a playlist based on the mood of the city provided.
     * @param city - city name
     * @return a playlist (collection of tracks)
     * @throws InvalidGenreException if a genre based on the city temperature could not be found
     * @throws ExternalServiceException if something goes wrong when consuming a web service
     */
    @GetMapping("/{city}")
    public ResponseEntity<List<TrackInfoDTO>> getMood(@PathVariable("city") String city) throws InvalidGenreException, ExternalServiceException {
        try {
            return ResponseEntity.ok(moodService.getMood(city));
        } catch (InvalidGenreException | ExternalServiceException exception){
            logger.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    /**
     * Returns a playlist based on the mood of the city provided.
     * @param lat latitude
     * @param lon longitude
     * @return a playlist (collection of tracks)
     * @throws InvalidGenreException if a genre based on the city temperature could not be found
     * @throws ExternalServiceException if something goes wrong when consuming a web service
     */
    @GetMapping("/{lat}/{lon}")
    public ResponseEntity<List<TrackInfoDTO>> getMood(@PathVariable("lat") Double lat, @PathVariable("lon") Double lon) throws InvalidGenreException, ExternalServiceException {
        try{
            return ResponseEntity.ok(moodService.getMood(lat, lon));
        } catch (InvalidGenreException  | ExternalServiceException exception){
            logger.error(exception.getMessage(), exception);
            throw exception;
        }
    }

}

