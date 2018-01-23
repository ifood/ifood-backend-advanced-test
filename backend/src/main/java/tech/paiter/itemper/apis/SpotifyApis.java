package tech.paiter.itemper.apis;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import tech.paiter.itemper.apis.builders.MusicaBuilder;
import tech.paiter.itemper.apis.dto.Musica;
import tech.paiter.itemper.apis.dto.Playlist;
import tech.paiter.itemper.apis.dto.TokenSpotify;

import java.io.IOException;
import java.util.*;

@Component
public class SpotifyApis {

    @Autowired
    SpotifyUtil spotifyUtil;

    public List<Musica> getPlaylistByCategoria(String categoria) throws IOException {

        String Uri = String.format("https://api.spotify.com/v1/browse/categories/%s/playlists?country=BR&offset=0&limit=1", categoria);

        try {

            String responseBody = queryUrl(Uri);

            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(responseBody).getAsJsonObject();
            JsonObject playlistsJson = obj.get("playlists").getAsJsonObject();
            JsonArray items = playlistsJson.get("items").getAsJsonArray();

            List<Musica> musicas = new ArrayList();
            for (Iterator item = items.iterator(); item.hasNext(); ) {
                JsonObject i = (JsonObject) item.next();
                JsonObject tracks = i.get("tracks").getAsJsonObject();
                String tracksMusics = tracks.get("href").getAsString();
                musicas.addAll(getTracksByPlaylist(tracksMusics));
            }

            // Gson gson = new Gson();
            // return gson.fromJson(responseBody, TokenSpotify.class);
            return musicas;

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public List<Musica> getTracksByPlaylist(String playlistUrl) throws IOException {

        try {

            String responseBody = queryUrl(playlistUrl);

            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(responseBody).getAsJsonObject();
            JsonArray items = obj.get("items").getAsJsonArray();

            List<Musica> musicas = new ArrayList();
            for (Iterator item = items.iterator(); item.hasNext(); ) {
                JsonObject i = (JsonObject) item.next();
                JsonObject track = i.get("track").getAsJsonObject();
                Musica musica = new MusicaBuilder()
                                    .setId(track.get("id").getAsString())
                                    .setNome(track.get("name").getAsString())
                                    .setHref(track.get("href").getAsString())
                                    .constroiOpenHref()
                                    .build();

                musicas.add(musica);
            }

            return musicas;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String queryUrl(String uri) throws IOException {

        TokenSpotify token = spotifyUtil.getToken();

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader("Authorization", "Bearer " + token.getAccess_token());
        httpGet.addHeader("Accept", MediaType.APPLICATION_JSON_VALUE);

        // Create a custom response handler
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity responseEntity = response.getEntity();
                return responseEntity != null ? EntityUtils.toString(responseEntity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        return httpClient.execute(httpGet, responseHandler);
    }

}