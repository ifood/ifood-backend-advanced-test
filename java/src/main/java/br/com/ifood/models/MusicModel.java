package br.com.ifood.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "music")
public class MusicModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String lat;

	@Column(nullable = false)
	private String lon;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private String spotify;

	@Column(nullable = false)
	private Integer temp;
	
	@Column(nullable = false)
	private Integer temp_min;
	
	@Column(nullable = false)
	private Integer humidity;
	
	@Column(nullable = false)
	private Integer pressure;

	@Column(nullable = false)
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
