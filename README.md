# iMood - Moods for any weather

**iMood** is a service able to accept RESTful requests receiving as parameter either city or coordinates (latitude, longitude) and returns you a playlist according to the current temperature of that location, guaranteeing you songs that match the mood of the weather!

## Running

### .jar file
In the docker folder, you'll find the _file imood.jar_.
You may run the command `java -jar imood.jar` in that folder to start the application.

### docker-compose
If you have docker-compose installed, in the docker folder you can just run the command `docker-compose up` to create a docker container and start the application.

## End-Points

The application starts up on _port 8080_.

* Request `localhost:8080/mood/{city_name}` to get a playlist according to the city especified.
* Request `localhost:8080/mood/{latitude}/{longitude}` to get a playlist according to the city coordinates.

## Details

The weather information was gathered using the *OpenWeather API* (https://openweathermap.org) and the songs where fetched from *Spotify API* (https://developer.spotify.com/documentation/web-api).
The project uses maven and was built using primarily SpringBoot and SpringMVC. SpringCloud was used as well for the OAuth2 authentication (requested by Spotify) and the declarative syntax for REST clients (Feign). Lombok was also used to simplify Getters, Setters and Constructors writing.
