package ifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SpotifyUnnauthorizedException extends BaseException {

    private static final String FULL_MESSAGE = "Acesso não autorizado (verifique o token de acesso). Mensagem original: [%s]";

    public SpotifyUnnauthorizedException(final Throwable cause) {
        super(String.format(FULL_MESSAGE, cause.getMessage()), cause, ExceptionOriginEnum.SPOTIFY_APY);
    }

    public SpotifyUnnauthorizedException(final String message) {
        super(String.format(FULL_MESSAGE, message), ExceptionOriginEnum.SPOTIFY_APY);
    }
}
