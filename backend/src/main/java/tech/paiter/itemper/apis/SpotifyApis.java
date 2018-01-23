package tech.paiter.itemper.apis;

import com.google.gson.Gson;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import tech.paiter.itemper.apis.dto.TokenSpotify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpotifyApis {

    @Autowired
    SpotifyUtil spotifyUtil;

    public void getPlaylistByCategoria(String categoria) throws IOException {

        TokenSpotify token = spotifyUtil.getToken();

        String Uri = String.format("https://api.spotify.com/v1/browse/categories/%s/playlists?country=BR&offset=0&limit=2", categoria);
        // String.format("https://api.spotify.com/v1/browse/categories/%s", categoria);

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(Uri);
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

            String responseBody = httpClient.execute(httpGet, responseHandler);

            System.out.println("----------------------------------------");
            System.out.println(responseBody);

            // Gson gson = new Gson();
            // return gson.fromJson(responseBody, TokenSpotify.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

}
