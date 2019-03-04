package br.com.ifood.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.ifood.dto.MusicDto;
import br.com.ifood.exceptions.HandlerError;
import br.com.ifood.services.MusicService;
import br.com.ifood.services.WeatherService;

@RestController
@RequestMapping("/api/v1")
public class MusicController extends HandlerError {

	@Autowired
	MusicService musicService;

	@Autowired
	WeatherService weatherService;

	@GetMapping("/music")
	@ResponseBody
	public ResponseEntity<?> all() {
		return ResponseEntity.ok(musicService.listAll());
	}

	@PostMapping("/music")
	@ResponseBody
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid MusicDto musicDto, Errors errors) {
		try {
			if (errors.hasErrors())
				return ResponseEntity.badRequest().body(getFieldsErrors(errors));

			JSONObject json = weatherService.requestWeather(musicDto);

			musicDto = weatherService.jsonParseRequest(json, musicDto);
			
			String category = weatherService.searchCategoryBaseTemp(musicDto);

			JSONObject jsonServiceSpotify = musicService.searchMusicSpotifyCategory(category);

			jsonServiceSpotify = musicService.parseJsonReturn(jsonServiceSpotify);

			musicDto.setCategory(category);

 			return ResponseEntity.ok().body(musicService.save(jsonServiceSpotify.toString(), musicDto));

		} catch (JsonMappingException mappingException) {
			return ResponseEntity.badRequest().body("Error in search music in lat e lon");
		} catch (JsonParseException jsonParse) {
			return ResponseEntity.badRequest().body("Error in search music in lat e lon");
		} catch (JSONException jsonException) {
			return ResponseEntity.badRequest().body("Error in search music in lat e lon");
		} catch (IOException e) {
			return ResponseEntity.badRequest().body("Error in search music in lat e lon");
		}
	}

}
