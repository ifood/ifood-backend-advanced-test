package ifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SpotifyInvalidDataException extends BaseException {

    private static final String FULL_MESSAGE = "Dados não encontrados: [additionalInfo:%s] [url:%s]";

    public SpotifyInvalidDataException(final String additionalInfo, final String url, final Throwable cause) {
        super(String.format(FULL_MESSAGE, additionalInfo, url), cause, ExceptionOriginEnum.SPOTIFY_APY);
    }

    public SpotifyInvalidDataException(final String additionalInfo, final String url) {
        super(String.format(FULL_MESSAGE, additionalInfo, url), ExceptionOriginEnum.SPOTIFY_APY);
    }
}
