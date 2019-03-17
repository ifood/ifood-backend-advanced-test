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

------------------------------------------------------------------------------------------------------------------------

## Resolution Explanations
The CelsiusTracks App retrieves a random playlist (only one) based on the category that is gotten through a pre-defined business rules.
This business rules are configured at the database table. If it will be needed to change the rules, it is as easy as to update or insert data in the database (it is not needed to change the code).
If there is no playlist for a category, a playlist fallback is retrieved.

For test purpose, access http://localhost:8080/swagger-ui.html

### Technologies/frameworks:
Spring Boot - easy and fast to setup the application. It makes the development process easier and faster
Spring Data - abstraction for ORM and repositories
Spring Cloud (Feign, Hystrix) - makes the integration faster to developer and reliable with fallback implementations
Swagger - API documentation 
Redis - to cache the results for a predefined amount of time to minimize the throughput at the Spotify and OpenWeatherMap APIs
Docker - to setup the infrastructure - runs the application database and redis for cache

## Possible Next Steps
- Add newRelic for monitoring app
- Implement Hateoas if needs
