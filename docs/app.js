/**
    * @api {post} /api/v1/music Search Music
    * @apiGroup Music
    * @apiVersion 1.0.0
    * @apiSuccess {String} lat Music lat is not empty
    * @apiSuccess {String} lon Music lon is not empty
    * @apiExample {curl} Example:
        curl -X POST http://localhost:3000/api/v1/music \
        -H 'Content-Type: application/json' \
        -H 'cache-control: no-cache' \
        -d '{
            "lat": "43.653225",
            "lon": "-79.383186"
        }'
    * @apiSuccessExample {json} Success
    *    HTTP/1.1 200 OK
        {
            "id": 4,
            "lat": "43.653225",
            "lon": "-79.383186",
            "category": "classical",
            "spotify": "https://open.spotify.com/playlist/37i9dQZF1DX4s3V2rTswzO",
            "temp": -11,
            "temp_min": -15,
            "humidity": 72,
            "pressure": 1016,
            "temp_max": -7
        }
    * @apiErrorExample {json} Erorr integration
    *    HTTP/1.1 404 Bad Request
    *  Error in search music in lat e lon

    * @apiErrorExample {json} Empty Body
    *    HTTP/1.1 404 Bad Request
        {
            "lon": "não pode estar em branco",
            "lat": "não pode estar em branco"
        }
    * @apiErrorExample {json} Create Music error
    *    HTTP/1.1 500 Internal Server Error
**/

/**
   * @api {get} /api/v1/music Find All Music
   * @apiGroup Music
   * @apiVersion 1.0.0
   * @apiExample {curl} Example:
    curl  http://localhost:3000/api/v1/music
    -H 'Cache-Control: no-cache' \
    -H 'Content-Type: application/json'
   * @apiSuccessExample {json} Success
   *    HTTP/1.1 200 OK
   [
        {
            "id": 4,
            "lat": "43.653225",
            "lon": "-79.383186",
            "category": "classical",
            "spotify": "https://open.spotify.com/playlist/37i9dQZF1DX4s3V2rTswzO",
            "temp": -11,
            "temp_min": -15,
            "humidity": 72,
            "pressure": 1016,
            "temp_max": -7
        }
    ]
   * @apiErrorExample {json} Find All error
   *    HTTP/1.1 500 Internal Server Error
**/
