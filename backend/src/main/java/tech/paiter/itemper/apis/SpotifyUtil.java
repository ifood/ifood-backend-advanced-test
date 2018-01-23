package tech.paiter.itemper.apis;

import com.google.gson.Gson;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.paiter.itemper.apis.dto.TokenSpotify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpotifyUtil {

    @Value("${api.key.spotify.client.id}")
    private  String clientId;

    @Value("${api.key.spotify.client.secret}")
    private String clientSecret;

    @Value("${api.key.spotify.redirect.url}")
    private String redirectUrl;

    @Value("${api.key.spotify.authorization.base64}")
    private String authorization;


    @Value("${api.key.spotify.url.token}")
    private String urlToken;


    public TokenSpotify getToken() throws IOException {

        try {
            Gson gson = new Gson();

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlToken);
            httpPost.addHeader("Authorization", authorization);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");


            List<NameValuePair> form = new ArrayList<>();
            form.add(new BasicNameValuePair("grant_type", "client_credentials"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);

            httpPost.setEntity(entity);

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

            String responseBody = httpClient.execute(httpPost, responseHandler);
//             System.out.println("----------------------------------------");
//             System.out.println(responseBody);
            return gson.fromJson(responseBody, TokenSpotify.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
