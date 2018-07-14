package ifood.model;

import ifood.exception.ExceptionOriginEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaylistErrorReturn {

    private ExceptionOriginEnum errorOrigin;

    private String message;

    private String uri;
}
