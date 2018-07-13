package ifood.exception;

import ifood.model.TrackCategoryEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpotifyInvalidCategoryException extends BaseException {

    private static final String FULL_MESSAGE = "Dados não encontrados: [trackCategory:%s] [country:%s]";

    public SpotifyInvalidCategoryException(final TrackCategoryEnum trackCategory, final String country, final Throwable cause) {
        super(String.format(FULL_MESSAGE, trackCategory, country), cause, ExceptionOriginEnum.SPOTIFY_APY);
    }

    public SpotifyInvalidCategoryException(final TrackCategoryEnum trackCategory, final String country) {
        super(String.format(FULL_MESSAGE, trackCategory, country), ExceptionOriginEnum.SPOTIFY_APY);
    }
}
