# iFood Backend Advanced Test

Create a micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Architecture

The application gateway is the controller, *RecommendationController*, that provides two different interfaces:
- Get tracks by city name, and;
- Get tracks by location.

Both interfaces call the service *RecommendationService* that is responsible to perform the application business flow:
- Obtain the weather temperature from *OpenWeatherMap* considering the input place;
- Obtain the track category based on the weather temperature, and;
- Obtain the tracks name based on the category.

### OpenWeatherMap
To extract he temperature from *OpenWeatherMap*, it was used the *owm-japis*.
This library is recommended by the *OpenWeatherMap* for Java applications, it allows to handle the requests to *OpenWeatherMap* without dealing REST request itself.
The library provides the same flexibility as the REST call, however, it is easy to use and maintained.

### Spotify
Spotify provides few APIs to handle the request.
For this project, *spotify-web-api-java* was used, it provides a powerful way of handle the Spotify features.
The main reason of use it is providing a unify and easy way of access different Spotify feature for further implementations.

To access Spotify, it is needed a authentication procedure that results in a *access token*.
The *access token* has a valid period, that way, the application is prepared to validate the *access token* by reconnecting.

### Business Rules
To keep the business rules separated from other concerns, the package *businessrules* was created.
It has only one rule that categorizes the wanted track type by the received temperature.

## Requirement's Business Rules
* If temperature (Celcius) is above 30 degrees, suggest tracks for party
* In case temperature is above 15 and below 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks
