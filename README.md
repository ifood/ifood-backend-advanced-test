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

## Sobre o projeto

# Keywords de Tecnologias

* Desenvolvimento: Java, Gradle, SpringBoot, IntelliJ, Lonbock, Cache (Spring) ...
* Testes: JUnit, Wiremock, Mockito.
* Documebntação de endponts: Swagger UI.
* Validação da cobertura de testes: Jacoco Reports.
* Container: Docker.

# Para executar o projeto (local)

1. Na raíz do projeto execute `./gradlew bootRun.
2. O projeto estará disponível em http://localhost:8080/

# Para visualizar a documentação dos endpoints

Para a documebntação dos endpoints a interface do Swagger estará disponível utilizando um navegador em http://localhost:8080/.

# Para executar os testes

1. Na raíz do projeto execute `./gradlew clean build`.
2. Com todos os testes executados sem falhar, o relatório final dos testes estará disponível em "/build/reports/jacacoco/test/html/index.html".

## Testando a API

# Pré requisitos

Possuir um token válido do Spotify, devidamente autorizado para a aplicação:

1. Via navegador, acesse o endereço https://accounts.spotify.com/authorize/?client_id=debe59ce56604405ad2f198cccb8ab2a&response_type=token&redirect_uri=https%3A%2F%2Fexample.com%2Fcallback&scope=playlist-read-private%20playlist-read-collaborative
2. Em caso de primeiro acesso você será redirecionado para o Spotify, solicitando seu login e sua permissão para que a aplicação acesse seus dados (client_id debe59ce56604405ad2f198cccb8ab2a).
3. Você será redirecionado para uma página em https://example.com/ onde deverá copiar a queryString **access_token**.
4. Pronto! esse é o seu **Spotify-Token** que deverá ser utilizado nos Headers dos próximos requests.

# Buscando uma playlist por nome da cidade

Exemplo de requisição com a aplicação sendo executada local, na porta 8080:

```bash
curl -X GET \
  http://localhost:8080/playlists/city/campinas \
  -H 'cache-control: no-cache' \
  -H 'postman-token: f2a18f88-d813-eac0-0fdb-0da977a5f1f6' \
  -H 'spotify-token: BQDD8tTGtn6sgOjFQpg-FaxTafkxtXAgHYXcZz1689kKrfSE4eC2q3pBfcCnYli7eeapN7lzZfMM2kTD9Tve8hIx0qqgTD0TFVwWJkPwuyzGm6JdkTzupfZ6ykcw4fZnsubNeJAGKMWDYHW9wCTzIwCC0N1suGd63SUFCQa7ihaZ5x4tqgnq0bOFDAqq'

# Buscando uma playlists por posição geográfica (latitude e lonfitude)

Exemplo de requisição com a aplicação sendo executada local, na porta 8080:

```bash
curl -X GET \
  http://localhost:8080/playlists/lat/-22.91/lon/-47.06 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: cfa79c9a-8285-c6b8-89a2-7a71008706e6' \
  -H 'spotify-token: BQDD8tTGtn6sgOjFQpg-FaxTafkxtXAgHYXcZz1689kKrfSE4eC2q3pBfcCnYli7eeapN7lzZfMM2kTD9Tve8hIx0qqgTD0TFVwWJkPwuyzGm6JdkTzupfZ6ykcw4fZnsubNeJAGKMWDYHW9wCTzIwCC0N1suGd63SUFCQa7ihaZ5x4tqgnq0bOFDAqq'

# Resposta

A API retorna objetos do tipo ifood.model.SpotifyTrackData.

Exemplo de resposta
```bash
[
    {
        "name": "Summertime Magic"
    },
    {
        "name": "Youngblood"
    },
    {
        "name": "X - Remix"
    },
    {
        "name": "Audio - with Sia, Diplo & Labrinth"
    },
    {
        "name": "In My Feelings"
    },
    {
        "name": "no tears left to cry"
    },
    {
        "name": "Solo (feat. Demi Lovato)"
    },
    {
        "name": "Growing Pains"
    },
    {
        "name": "Entertainer"
    },
    {
        "name": "APESHIT"
    },
    {
        "name": "Ocean (feat. Khalid)"
    },
    {
        "name": "One Kiss (with Dua Lipa)"
    },
    {
        "name": "Rise"
    }
]

# Exemplo de erro

Os erros são tratados pelo ErrorHandlew e possuem sempre os campos origin, message e uri, representados pela classe ifood.model.PlaylistErrorReturn.

Exemplo:
```bash
{
    "origin": "OPEN_WEATHER_API",
    "message": "Dados não encontrados: [cityname:cidade invalida] [lat:null] [lon:null]",
    "uri": "/playlists/city/cidade%20invalida"
}