package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.bo.WeatherPlaylistBO;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.PlaylistServiceProxy;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.WeatherServiceProxy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherPlaylistBOTest {

    private static final String PARTY = "PARTY";
    private static final String POP = "POP";
    private static final String ROCK = "ROCK";
    private static final String CLASSICAL = "CLASSICAL";

    @Autowired
    private WeatherPlaylistBO weatherPlaylistBO;

    @MockBean
    private PlaylistServiceProxy playListServiceProxy;

    @MockBean
    private WeatherServiceProxy weatherServiceProxy;

    private static final String CAMPINAS = "Campinas";
    private static final double CAMPINAS_LATITUDE = -22.91D;
    private static final double CAMPINAS_LONGITUDE = -47.06D;
    private static final Double TEMP_GREATER_30 = 31D;
    private static final Double TEMP_BETWEEN_15_30 = 20D;
    private static final Double TEMP_BETWEEN_10_14 = 12D;
    private static final Double TEMP_BELLOW_10 = 5D;

    private static final List<String> PLAYLIST_FOR_PARTY = new ArrayList<>(Arrays.asList("partyTrack1", "partyTrack2", "partyTrack3", "partyTrack4"));
    private static final List<String> PLAYLIST_FOR_POP = new ArrayList<>(Arrays.asList("popTrack1", "popTrack2", "popTrack3", "popTrack4"));
    private static final List<String> PLAYLIST_FOR_ROCK = new ArrayList<>(Arrays.asList("rockTrack1", "rockTrack2", "rockTrack3", "rockTrack4"));
    private static final List<String> PLAYLIST_FOR_CLASSICAL = new ArrayList<>(
            Arrays.asList("classicalTrack1", "classicalTrack2", "classicalTrack3", "classicalTrack4"));

    private List<String> retrievedPlaylist;

    @Before
    public void setUp() {
        retrievedPlaylist = null;
    }

    @Test
    public void getPlaylistTrackNamesByCityName_whenTempGreaterThan30_thenReturnPartyPlaylist() {
        givenThatThereIsAValidCityNameWithTemperatureGreaterThan30(CAMPINAS);
        givenThatThereIsAPartyPlaylist();
        whenWeCallGetPlaylistTrackNamesByCityName();
        thenAPartyPlaylistHasBeenRetrieved();
    }

    @Test
    public void getPlaylistTrackNamesByCityName_whenTempBetween_15_30_thenReturnPopPlaylist() {
        givenThatThereIsAValidCityNameWithTemperatureBetween_15_30(CAMPINAS);
        givenThatThereIsAPopPlaylist();
        whenWeCallGetPlaylistTrackNamesByCityName();
        thenAPopPlaylistHasBeenRetrieved();
    }

    @Test
    public void getPlaylistTrackNamesByCityName_whenTempBetween_10_14_thenReturnRockPlaylist() {
        givenThatThereIsAValidCityNameWithTemperatureBetween_10_14(CAMPINAS);
        givenThatThereIsARockPlaylist();
        whenWeCallGetPlaylistTrackNamesByCityName();
        thenARockPlaylistHasBeenRetrieved();
    }

    @Test
    public void getPlaylistTrackNamesByCityName_whenTempBellow10_thenReturnClassicalPlaylist() {
        givenThatThereIsAValidCityNameWithTemperatureBellow10(CAMPINAS);
        givenThatThereIsAClassicalPlaylist();
        whenWeCallGetPlaylistTrackNamesByCityName();
        thenAClassicalPlaylistHasBeenRetrieved();
    }

    @Test
    public void getPlaylistTrackNamesByGeoCoordinates_whenTempGreaterThan30_thenReturnPartyPlaylist() {
        givenThatThereIsAValidCityGeoCoordsWithTemperatureGreaterThan30(CAMPINAS_LATITUDE, CAMPINAS_LONGITUDE);
        givenThatThereIsAPartyPlaylist();
        whenWeCallGetPlaylistTrackNamesByGeoCoordinates();
        thenAPartyPlaylistHasBeenRetrieved();
    }

    @Test
    public void getPlaylistTrackNamesByGeoCoordinates_whenTempBetween_15_30_thenReturnPopPlaylist() {
        givenThatThereIsAValidCityGeoCoordsWithTemperatureBetween_15_30(CAMPINAS_LATITUDE, CAMPINAS_LONGITUDE);
        givenThatThereIsAPopPlaylist();
        whenWeCallGetPlaylistTrackNamesByGeoCoordinates();
        thenAPopPlaylistHasBeenRetrieved();
    }

    @Test
    public void getPlaylistTrackNamesByGeoCoordinates_whenTempBetween_10_14_thenReturnRockPlaylist() {
        givenThatThereIsAValidCityGeoCoordsWithTemperatureBetween_10_14(CAMPINAS_LATITUDE, CAMPINAS_LONGITUDE);
        givenThatThereIsARockPlaylist();
        whenWeCallGetPlaylistTrackNamesByGeoCoordinates();
        thenARockPlaylistHasBeenRetrieved();
    }

    @Test
    public void getPlaylistTrackNamesByGeoCoordinates_whenTempBellow10_thenReturnClassicalPlaylist() {
        givenThatThereIsAValidCityGeoCoordsWithTemperatureBellow10(CAMPINAS_LATITUDE, CAMPINAS_LONGITUDE);
        givenThatThereIsAClassicalPlaylist();
        whenWeCallGetPlaylistTrackNamesByGeoCoordinates();
        thenAClassicalPlaylistHasBeenRetrieved();
    }

    // GIVEN

    private void givenThatThereIsAValidCityNameWithTemperatureGreaterThan30(String cityName) {
        when(weatherServiceProxy.retrieveCurrentTemperatureByCity(Mockito.anyString())).thenReturn(TEMP_GREATER_30);

    }

    private void givenThatThereIsAPartyPlaylist() {
        when(playListServiceProxy.retrieveSoundtrackNamesByGenre(PARTY)).thenReturn(PLAYLIST_FOR_PARTY);
    }

    private void givenThatThereIsAValidCityNameWithTemperatureBetween_15_30(String campinas2) {
        when(weatherServiceProxy.retrieveCurrentTemperatureByCity(Mockito.anyString())).thenReturn(TEMP_BETWEEN_15_30);
    }

    private void givenThatThereIsAValidCityNameWithTemperatureBetween_10_14(String campinas2) {
        when(weatherServiceProxy.retrieveCurrentTemperatureByCity(Mockito.anyString())).thenReturn(TEMP_BETWEEN_10_14);
    }

    private void givenThatThereIsAValidCityNameWithTemperatureBellow10(String campinas2) {
        when(weatherServiceProxy.retrieveCurrentTemperatureByCity(Mockito.anyString())).thenReturn(TEMP_BELLOW_10);
    }

    private void givenThatThereIsAPopPlaylist() {
        when(playListServiceProxy.retrieveSoundtrackNamesByGenre(POP)).thenReturn(PLAYLIST_FOR_POP);
    }

    private void givenThatThereIsARockPlaylist() {
        when(playListServiceProxy.retrieveSoundtrackNamesByGenre(ROCK)).thenReturn(PLAYLIST_FOR_ROCK);
    }

    private void givenThatThereIsAClassicalPlaylist() {
        when(playListServiceProxy.retrieveSoundtrackNamesByGenre(CLASSICAL)).thenReturn(PLAYLIST_FOR_CLASSICAL);
    }

    private void givenThatThereIsAValidCityGeoCoordsWithTemperatureGreaterThan30(double campinasLatitude, double campinasLongitude) {
        when(weatherServiceProxy.retrieveCurrentTemperatureByGeoCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(TEMP_GREATER_30);
    }

    private void givenThatThereIsAValidCityGeoCoordsWithTemperatureBetween_15_30(double campinasLatitude, double campinasLongitude) {
        when(weatherServiceProxy.retrieveCurrentTemperatureByGeoCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(TEMP_BETWEEN_15_30);
    }

    private void givenThatThereIsAValidCityGeoCoordsWithTemperatureBetween_10_14(double campinasLatitude, double campinasLongitude) {
        when(weatherServiceProxy.retrieveCurrentTemperatureByGeoCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(TEMP_BETWEEN_10_14);
    }

    private void givenThatThereIsAValidCityGeoCoordsWithTemperatureBellow10(double campinasLatitude, double campinasLongitude) {
        when(weatherServiceProxy.retrieveCurrentTemperatureByGeoCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(TEMP_BELLOW_10);
    }

    // WHEN

    private void whenWeCallGetPlaylistTrackNamesByCityName() {
        retrievedPlaylist = weatherPlaylistBO.getPlaylistTrackNamesByCityName(CAMPINAS);
    }

    private void whenWeCallGetPlaylistTrackNamesByGeoCoordinates() {
        retrievedPlaylist = weatherPlaylistBO.getPlaylistTrackNamesByGeoCoordinates(CAMPINAS_LATITUDE, CAMPINAS_LONGITUDE);
    }

    // THEN

    private void thenAPartyPlaylistHasBeenRetrieved() {
        assertTrue(PLAYLIST_FOR_PARTY.equals(retrievedPlaylist));

    }

    private void thenAPopPlaylistHasBeenRetrieved() {
        assertTrue(PLAYLIST_FOR_POP.equals(retrievedPlaylist));
    }

    private void thenARockPlaylistHasBeenRetrieved() {
        assertTrue(PLAYLIST_FOR_ROCK.equals(retrievedPlaylist));
    }

    private void thenAClassicalPlaylistHasBeenRetrieved() {
        assertTrue(PLAYLIST_FOR_CLASSICAL.equals(retrievedPlaylist));
    }

}
