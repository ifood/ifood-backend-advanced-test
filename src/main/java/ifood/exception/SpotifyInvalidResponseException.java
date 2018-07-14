package ifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class SpotifyInvalidResponseException extends BaseException {

    private static final String FULL_MESSAGE = "Erro ao obter dados de spotify: [url:%s]";

    public SpotifyInvalidResponseException(final String url, final Throwable cause) {
        super(String.format(FULL_MESSAGE, url), cause, ExceptionOriginEnum.SPOTIFY_APY);
    }
}
