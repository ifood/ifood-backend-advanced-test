Eu realizei o desenvolvimento em um repositório que criei no GitHub (https://github.com/ffrazato/microservices-study).
 
Explicando resumidamente o que eu fiz:
 
Primeiramente, eu iniciei o desenvolvimento de somente um microserviço, como foi pedido, que pode ser visto no repositório (https://github.com/ffrazato/wheatherplaylist). Basicamente este microserviço possui 1 controller e 1 business object que é responsável por cuidar da lógica de negócio para adquirir a temperatura e a playlist, através dos provedores que utilizam as APIs do Spotify e do OpenWeatherMap.
 
Porém eu percebi que não seria possível fazer a solução que eu havia pensado utilizando somente com 1 microserviço, por isso, quebrei em 3 microserviços:
1. (Weather-Service) Um para cuidar somente da temperatura, contendo seu próprio BO, provider e controller.
2. (Playlist-Service) Outro para cuidar somente da playlist, também contendo seu próprio BO, provider e controller.
3. (Weather-Playlist-Service) E por fim um último que utiliza os dois microserviços acima para adquirir a temperature e executar a lógica de negócio citada no case e adquirir a playlist.
 
Todos os microserviços foram criados utilizando Gradle 5.1, Spring Boot 2.1.1 , Java 8 e o Spring Cloud Framework 2.0.2 que utiliza soluções opensource do Netflix OSS.
 
O último microserviço citado (Weather-playlist-service), faz a comunicação com os outros 2 (weather e playlist) utilizando:
1. Feign (para facilitar a comunicação com clients REST)
2. Ribbon (para fazer o balanceamento de carga do lado cliente)
3. Hystrix (para ter tolerância a falhas, implementando o padrão de projeto Circuit Breaker).
 
Como o Ribbon foi utilizado precisei também incluir o Eureka Naming Server, para que os serviços se registrassem no mesmo e o Ribbon fosse capaz de encontra-los, evitando assim deixar os endereços dos microserviços hardcoded.
 
Eu levantei somente uma instância de cada microserviço já que não existe a necessidade de criar mais de uma, mas o ribbon conseguiria trabalhar com bem mais caso exista a necessidade.
 
Existem melhorias a serem feitas que basicamente não implementei pois como nunca havia mexido com microserviços, resolvi focar principalmente no estudo de microserviços. Outro ponto importante é que consegui trabalhar neste case somente nos finais de semana (últimos 3) pois o projeto está corrido e tivemos o natal e ano novo.
 
Seguem algumas melhorias:
 
1. Melhorar a cobertura do teste unitário e implementar novos, pois fiz somente a do weather-playlist-service, pois a lógica de negócio do case está toda nele e os outros microserviços, basicamente utilizam a API do spotify e openweathermap, por isso optei por implementar o unit test deste serviço, mas mesmo assim existem mais casos de teste que devem ser implementados.
2. Utilizar um mecanismo de log (por exemplo Log4J) para armazenar os logs do microserviço e se possível criar um serviço de logs.
3. Realizar um melhor tratamento das exceções para evitar que o serviço quebre e também para retornar algo útil e inteligível para o cliente.
4. Implementar ou utilizar um mecanismo de cache para tratar o cenário de quando o circuito estiver aberto, podendo utilizar valores anteriormente coletados para retornar a playlist para o cliente, se recuperando assim de possíveis falhas em outros microserviços.
5. Utilizar um arquivo de configurações para armazenar clientId, apikey entre outros dados relevantes para as APIs do spotify e openweathermap.
6. Separar os controllers em um pacote exclusivo.
7. Colocar os microserviços em containers docker. Eu mexi há 3 anos atrás com docker, inclusive utilizando o docker-compose porém desta vez achei mais rápido criar uma máquina virtual.
 
Estas são as melhorias que lembro até o momento, se eu lembrar de mais alguma te aviso.
 
Se você preferir posso enviar somente os JARs dos microserviços, a máquina virtual, lembrando que o eureka deve ser inicializado primeiro, pois os outros se registram nele.
Exemplos de chamadas:

1. localhost:8761 para acessar o Eureka
2. localhost:8300/playlist/campinas para adquirir playlist por nome de cidade
3. localhost:8300/playlist/-22.91/-47.06 para adiquirir por latitude e longitude respectivamente
 
Qualquer dúvida sobre os frameworks, padrões utilizados ou sobre qualquer outro assunto, não hesite em perguntar.
