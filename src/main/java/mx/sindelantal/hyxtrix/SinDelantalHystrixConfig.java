package mx.sindelantal.hyxtrix;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import net.aksingh.owmjapis.core.OWM;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Configuration
public class SinDelantalHystrixConfig {

	@Value("${owm.key}")
	private String owmKey;
	@Value("${spotifyApi.clientid}")
	private String clientID;
	@Value("${spotifyApi.clientSecret}")
	private String clientSecret;
	@Value("${cache.sindelantal.file}")
	private String cacheFile;
	
	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public OWM owsGenerator() {
		OWM owm = new OWM(owmKey);
		owm.setLanguage(OWM.Language.SPANISH);
		return owm;
	}

	@Bean
	public SpotifyApi spotifyCredential() throws SpotifyWebApiException {
		SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientID)
				.setClientSecret(clientSecret).build();
		ClientCredentialsRequest client = spotifyApi.clientCredentials().build();
		try {
			spotifyApi.setAccessToken(client.execute().getAccessToken());
		} catch (SpotifyWebApiException | IOException e) {
			e.printStackTrace();
			throw new SpotifyWebApiException("SpotifyApi API generator ERROR!!");
		}
		return spotifyApi;
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource(cacheFile));
		cmfb.setShared(true);
		return cmfb;
	}
	
}
