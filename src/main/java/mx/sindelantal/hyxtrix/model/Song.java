package mx.sindelantal.hyxtrix.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Data
@lombok.AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String songName;
}
