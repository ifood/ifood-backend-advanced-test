# iFood Backend Advanced Test

Create a micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is between 15 and 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks 

## Hints

You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Non functional requirements

As this service will be a worldwide success, it must be prepared to be fault tolerant, responsive and resilient.

Use whatever language, tools and frameworks you feel comfortable to, and briefly elaborate on your solution, architecture details, choice of patterns and frameworks.

Also, make it easy to deploy/run your service(s) locally (consider using some container/vm solution for this). Once done, share your code with us.


## Implemented solution description

A Java 8 middleware microservice application (clean architecture based), interacting
with OpenWeatherMap and Spotify.

From a user request, the application searches for the current climatic condition and, using a use-case to define the music genre according to the climatic condition, 
calls the Spotify api requesting a playlist of that genre and 5 songs from that playlist.


## Frameworks

* Spring Boot
* Spring MVC
* Spring Security
* OpenFeign
* Lombok
* Jackson
* Swagger
* Swagger UI
* Docker

## Using app

__Steps__

  Build app
  ```bash
    mvn clean install
  ```
  
  Build and start docker container
  ```bash
      docker-compose -up
  ```
  
  Calling through Swagger
  ```bash
  http://localhost:8080/swagger-ui.html
   ```
  
  Calling Rest endpoints
  ```bash
  http://localhost:8080/playlist?city=Campinas
  http://localhost:8080/playlist?city=Tokio
  http://localhost:8080/playlist?latitude=-23.44&longitude=-46.53
  ```
  
## Finish

Thank you very much for the opportunity, guys!