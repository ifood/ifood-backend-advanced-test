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

========================================================================================================================

##Solución
Las herramientas de trabajo que se usaron fueron las siguientes:


*Spring Boot: Se utilizo para realizar los microservicios:
*spotify-web-api-java

La api contiene solo dos URL´S  :

La primera es para poner cualquier lugar que se desee.
http://localhost:8080/api/tracks/place

Puede ser invocada desde cualquier herramienta que pueda lanzar peticiones como curl

Ejemplo:

curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{  \"lat\": 90, \"lon\": -90}" "http://localhost:8080/api/tracks/coord"

La Respuesta será la siguiente:

[
  "Ständchen, S. 560 (Trans. from Schwanengesang No. 4, D. 957)",
  "Partita for Violin Solo No.2 in D Minor, BWV 1004: 4. Gigue (Arr. for Mandolin by Avi Avital)",
  "Variation XVIII - Andante cantabile",
  "Shostakovich: Piano Quintet in G Minor, Op. 57: III. Scherzo. Allegretto",
  "Roll Over Beethoven: I. Allegro molto",
  "Piano Concerto No. 3 in C Major, Op. 26: I. Andante. Allegro - Live",
  "Piano Trio No. 1, Op. 49: III. Scherzo. Leggiero e vivace",
  "Ellis Island (feat. Simone Dinnerstein)",
  "Offenbach: Grand Concerto for Cello in G Major, \"Concerto Militaire\": II. Andante",
  "Concerto No. 4 in E-Flat Major, K.495: III. Rondo. Allegro Vivace"
]


La segunda es para poner las coordenadas del lugar
http://localhost:8080/api/tracks/coord

Ejemplo:
curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "Polanco" "http://localhost:8080/api/tracks/place"

[
  "Deberías Estar Aquí",
  "Te Esperé",
  "Si Tú Te Vas",
  "Te Confieso",
  "Baby Girl (feat. Lalo Ebratt)",
  "Ráptame",
  "La solución (feat. Carlos Rivera)",
  "Contigo Siempre",
  "Te Equivocaste (Primera Fila) - En Vivo",
  "Dime (feat. Reik)"
]

