package br.com.ifood.services;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ifood.dto.MusicDto;

@Service
public class WeatherService extends AbstractService {

	static String url = "https://openweathermap.org/data/2.5/weather/?appid=b6907d289e10d714a6e88b30761fae22";
	static String method = "GET";

	private String category = "classical";

	@Autowired
	private RequestService requestService;

	public JSONObject requestWeather(MusicDto musicDto) {
		JSONObject objectReturn = new JSONObject();
		System.out.println(url + "&lat=" + musicDto.getLat() + "&lon=" + musicDto.getLon());
		try {
			objectReturn = requestService.request(url + "&lat=" + musicDto.getLat() + "&lon=" + musicDto.getLon(),
					method, null);
			objectReturn = (JSONObject) objectReturn.get("main");

		} catch (MalformedURLException urlException) {
			urlException.printStackTrace();

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return objectReturn;
	}

	public MusicDto jsonParseRequest(JSONObject json, MusicDto musicDto)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper m = new ObjectMapper();
		
		MusicDto dto = m.readValue(json.toString(), musicDto.getClass());
		dto.setLat(musicDto.getLat());
		dto.setLon(musicDto.getLon());
		return dto;
	}

	public String searchCategoryBaseTemp(MusicDto musicDto) {
		if (musicDto.getTemp() > 30) category = "party";
		if (musicDto.getTemp() >= 15 && musicDto.getTemp() <= 30) category = "pop";
		if (musicDto.getTemp() >= 10 && musicDto.getTemp() <= 14) category = "rock";
		return category;
	}
}
