package br.com.ifood.challenge.celsiustracks.validator;

import br.com.ifood.challenge.celsiustracks.exception.BusinessLogicException;
import org.springframework.stereotype.Component;

@Component
public class CoordinateValidator {

    public void validateLatitude(final Double latitude) {
        if (latitude < -90d || latitude > 90d) {
            throw new BusinessLogicException("The latitude coordinate must be a value between -90 and 90");
        }
    }

    public void validateLongitude(final Double longitude) {
        if (longitude < -180d || longitude > 180d) {
            throw new BusinessLogicException("The longitude coordinate must be a value between -180 and 180");
        }
    }

    public void validateCoordinates(final Double latitude, final Double longitude) {
        validateLatitude(latitude);
        validateLongitude(longitude);
    }
}
