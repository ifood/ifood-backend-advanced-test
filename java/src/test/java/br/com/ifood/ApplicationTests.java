package br.com.ifood;

import static com.jayway.restassured.RestAssured.given;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;

import br.com.ifood.repositories.MusicRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource(value = { "classpath:application.properties" })
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTests {

	JSONObject musicJson = new JSONObject();

	private static MusicRepository musicRepository;

	@Autowired
	public void setRepository(MusicRepository customerRepository) {
		ApplicationTests.musicRepository = customerRepository;
	}

	@Before
	public void setup() throws JSONException {
		musicJson.put("lat", "43.653225");
		musicJson.put("lon", "-79.383186");
		RestAssured.port = 3000;
		RestAssured.baseURI = "http://localhost";
	}

	@AfterClass
	public static void drop() {
		musicRepository.deleteAll();
	}

	@Test
	public void searchMusicLatLon() {
		given().contentType("application/json").body(musicJson.toString()).when().post("/api/v1/music").then()
				.statusCode(HttpStatus.OK.value()).extract().response();
	}

}
