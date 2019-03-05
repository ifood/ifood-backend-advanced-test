package mx.sindelantal.hyxtrix.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Strings;

import lombok.Data;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Data
@lombok.AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientInformation {

	private String cityName;
	private Integer latitude;
	private Integer longitud;
	private Double temperature;
	
	public ClientInformation(Double temperature) {
		this.temperature = temperature;
	}
	public boolean isValidCityName() {
        return !Strings.isNullOrEmpty(cityName);
    }

    public boolean isValidCoordinates() {
        return (latitude!=null && longitud!=null);
    }
}
