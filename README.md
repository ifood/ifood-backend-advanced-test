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

## Instruction to run the app
1. Start docker container to run the database:
    docker run --name postgresDB -e POSTGRES_PASSWORD=secretpwd -e POSTGRES_DB=celsiustracks -p 5432:5432 -d postgres
2. Execute the scripts from "resources/sql"
3. Start the Spring Boot Application: run Application.java

## Resolution Explanations
The CelsiusTracks App retrieves a random playlist (only one) based on the category that is gotten through a pre-defined business rules.
This business rules are configured at the database table. If it will be needed to change the rules, it is as easy as to update or insert data in the database (it is not needed to change the code).
If there is no playlist for a category, a playlist fallback is retrieved.

For test purpose, access http://localhost:8080/swagger-ui.html

### Main Technologies/Frameworks:
Spring Boot - easy and fast to setup the application. It makes the development process easier and faster
Spring Data - abstraction for ORM and repositories
Spring Cloud (Feign, Hystrix) - makes the integration faster to developer and reliable with fallback implementations
Swagger - API documentation 
Docker - to setup the infrastructure - runs the application database
Postgres DB - Database to keep the category and category by temperature range datas
Fixture Template - for test data generation (used in Unit Tests)
Jacoco - for line coverage
Lombok - to make the code cleaner and more readable.

## Possible Next Steps
- Improve test coverage and add integration tests
- Add Sonar for code quality
- Add newRelic for monitoring app
- Use Redis to cache the results for a predefined amount of time to minimize the throughput at the Spotify and OpenWeatherMap APIs
- Improve fallback quality, for instance, use cache or database to keep some tracks searched recently. 
There are other specific points to add fallback strategy.
- Implement Hateoas (if needs)