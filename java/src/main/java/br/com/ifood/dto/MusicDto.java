package br.com.ifood.dto;

import javax.validation.constraints.NotBlank;

public class MusicDto {

	private Long id;
	
	@NotBlank
	private String lat;
	@NotBlank
	private String lon;

	private String category;

	private String spotify;

	private Integer temp;

	private Integer temp_min;

	private Integer humidity;

	private Integer pressure;

	private Integer temp_max;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSpotify() {
		return spotify;
	}

	public void setSpotify(String spotify) {
		this.spotify = spotify;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public Integer getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(Integer temp_min) {
		this.temp_min = temp_min;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Integer getPressure() {
		return pressure;
	}

	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}

	public Integer getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(Integer temp_max) {
		this.temp_max = temp_max;
	}

	
	

}
