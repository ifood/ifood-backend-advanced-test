package br.com.ifood.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

	private static HttpURLConnection con;

	public JSONObject request(String url, String method, String token) throws MalformedURLException, IOException {

		try  {
			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();
			
			con.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
			con.setRequestProperty("Accept","*/*");
			con.setRequestProperty("Content-Type", "application/json");
			
			if (token != null) con.setRequestProperty("Authorization", "Bearer " + token);
			
			con.setRequestMethod(method);
			StringBuffer content;
 
			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

				String line;
				content = new StringBuffer();

				while ((line = in.readLine()) != null) {
					content.append(line);
				}
			}

			return new JSONObject(content.toString());
		
		} finally {
			con.disconnect();
		}
	}
}
