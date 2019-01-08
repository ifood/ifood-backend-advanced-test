package io.brunodoescoding.dto.track;

import com.google.common.base.Strings;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaylistSuggestionDto {
    private final String lat;
    private final String lon;
    private final String city;

    public boolean isValid() {
        return (!Strings.isNullOrEmpty(city) || isValidCoordinates());
    }

    public boolean isValidCoordinates() {
        return !(Strings.isNullOrEmpty(lat) || Strings.isNullOrEmpty(lon));
    }
}
