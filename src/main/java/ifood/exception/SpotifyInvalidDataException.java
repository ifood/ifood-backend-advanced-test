package ifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SpotifyInvalidDataException extends BaseException {

    private static final String FULL_MESSAGE = "Dados não encontrados: [inputs:%s]";

    public SpotifyInvalidDataException(final String[] values) {
        super(String.format(FULL_MESSAGE, String.join(", ", values)), ExceptionOriginEnum.SPOTIFY_APY);
    }
}
