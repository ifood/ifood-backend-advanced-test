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

## Stack

* Java 8
* Spring Boot
* JUnit (for testing purposes)
* Mockito (for testing purposes)
* Guava (for caching purposes)

### Considerations
* I don't have enough knowledge dealing with Docker. Based on that, I didn't provide an image in order to run the project.
Sorry about that!
* As I'm running late, the client ids where not externalized to the application.resources file. In fact, they where but I haven't created the class to retrieve them. Oops! 
* In order to run the program and do the calls to the apis (spotify and openweather), it is required to update the following classes:
    * **SpotifyServiceImpl** (**the attributes which have a REPLACE_TOKEN_HERE**).
    * **TemperatureServiceImpl** (**the attributes which have a REPLACE_TOKEN_HERE**).
