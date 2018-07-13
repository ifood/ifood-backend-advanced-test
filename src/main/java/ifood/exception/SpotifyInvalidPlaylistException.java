package ifood.exception;

import ifood.model.TrackCategoryEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpotifyInvalidPlaylistException extends BaseException {

    private static final String FULL_MESSAGE = "Dados não encontrados: [playlist:%s]";

    public SpotifyInvalidPlaylistException(final String playlist, final Throwable cause) {
        super(String.format(FULL_MESSAGE, playlist), cause, ExceptionOriginEnum.SPOTIFY_APY);
    }

    public SpotifyInvalidPlaylistException(final String playlist) {
        super(String.format(FULL_MESSAGE, playlist), ExceptionOriginEnum.SPOTIFY_APY);
    }
}
