package br.com.ifood.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ifood.dto.MusicDto;
import br.com.ifood.models.MusicModel;
import br.com.ifood.repositories.MusicRepository;

@Service
public class MusicService extends AbstractService {

	private static String url = "https://api.spotify.com/v1/browse/categories/";
	private static String token = "BQDl65lIoG59Cg2UHQNHaCqlI_WCsrwaamG9ncwIB9Aftw29-BwRnsZlxlSTjwqBBG14WxIvFc9aP2OndJdn3VQLVP8D2PX7VYQgWWpnwJlRI3SVCHdZv_mJLNeRp9EgIP2W4MhgWTbZ_Ys";

	@Autowired
	private MusicRepository musicRepository;

	@Autowired
	private RequestService requestService;

	public List<MusicDto> listAll() {
		List<MusicModel> list = (List<MusicModel>) musicRepository.findAll();
		return convertList(list, MusicDto.class);
	}

	public MusicDto listOne(Long id) {
		return convertSimple(musicRepository.findById(id), MusicDto.class);
	}

	public MusicModel save(String json ,MusicDto  musicDto) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper m = new ObjectMapper();
		MusicDto jsonDto = m.readValue(json.toString(), MusicDto.class);
		musicDto.setSpotify(jsonDto.getSpotify());
		return musicRepository.save(convertSimple(musicDto, MusicModel.class));
	}

	public JSONObject searchMusicSpotifyCategory(String category) {
		JSONObject jsonResponse = new JSONObject();
		try {
			jsonResponse = requestService.request(url + category + "/playlists?country=BR&limit=10&offset=5", "GET",
					token);
			System.out.println(jsonResponse.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResponse;
	}

	public JSONObject parseJsonReturn (JSONObject json) {
		
		JSONObject jsonServiceSpotify = json.getJSONObject("playlists");
		JSONArray arrayJson = jsonServiceSpotify.getJSONArray("items");
		jsonServiceSpotify = new JSONObject(arrayJson.get(0).toString());
		return (JSONObject) jsonServiceSpotify.get("external_urls");
	}
}
