package mx.sindelantal.hyxtrix.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Strings;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import mx.sindelantal.hyxtrix.model.Song;
import mx.sindelantal.hyxtrix.service.InputService;
import net.aksingh.owmjapis.api.APIException;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Controller
public class InputController {
	protected static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private InputService inputService;

	@RequestMapping(value = "/city/{cityName}", method = RequestMethod.GET)
	public ResponseEntity<List<Song>> listByCity(@PathVariable(required=true) String cityName) throws SpotifyWebApiException, APIException, IOException {
		if (!Strings.isNullOrEmpty(cityName)) {
			List<Song> lstSong = inputService.findByCityName(cityName);
			if (lstSong ==null || lstSong.isEmpty())
				return new ResponseEntity<List<Song>>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Song>>(lstSong, HttpStatus.OK);
		} else {
			throw new IllegalArgumentException("You should inser one city name!");
		}
	}
	
	@RequestMapping(value = "/coo/{latitude}/{longitud}", method = RequestMethod.GET)
	public ResponseEntity<List<Song>> listByCoordinates(@PathVariable(required=true) Integer latitude,@PathVariable(required=true) Integer longitud) throws SpotifyWebApiException, APIException, IOException {
		if (latitude!=null && longitud!=null) {
			List<Song> lstSong = inputService.findByCoordinates(Double.valueOf(latitude), Double.valueOf(longitud));
			if (lstSong!=null && lstSong.isEmpty())
				return new ResponseEntity<List<Song>>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Song>>(lstSong, HttpStatus.OK);
		} else {
			throw new IllegalArgumentException("You should inser your latitude and longitude!");
		}
	}
	
	@ExceptionHandler(value = {NumberFormatException.class })
    void handleResourceNotFoundException(NumberFormatException e, HttpServletResponse response) throws IOException {
		LOG.error("You should insert a numeric values!");
		response.sendError(HttpStatus.BAD_REQUEST.value(),"You should insert a numeric values!");
    }

	@ExceptionHandler (value = {SpotifyWebApiException.class, APIException.class,IOException.class})
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		LOG.error("Please try again in some minutes.");
		response.sendError(HttpStatus.BAD_REQUEST.value(),"Please try again in some minutes.");
	}
}
