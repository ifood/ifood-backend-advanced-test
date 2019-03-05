package mx.sindelantal.hyxtrix;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import mx.sindelantal.hyxtrix.service.InputService;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest( classes = SinDelantalHystrixApplicationTests.class)
public class InputServiceTest{

	@MockBean
	public OWM owm;
	
	@MockBean
	SpotifyApi spotifyApi;
	
	@MockBean
	private InputService inputService;

	@Test
	public void findByCityName() throws APIException, SpotifyWebApiException, IOException {
		assertNotNull(inputService.findByCityName(Mockito.anyString()));
	}
	@Test
	public void findByCoordinates()
			throws APIException, SpotifyWebApiException, IOException {
		assertNotNull(inputService.findByCoordinates(Mockito.anyDouble(), Mockito.anyDouble()));
	}
	
	
}
