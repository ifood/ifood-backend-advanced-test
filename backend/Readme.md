# iFood Test

## Install owm-japis in your repository

Install this dependency to run features of OpenWeatherMap

        mvn install:install-file -Dfile=lib/owm-japis-2.5.2.1.jar -DgroupId=net.aksingh -DartifactId=owm-japis -Dversion=2.5.2.1 -DpomFile=lib/pom-owm-japis.xml

## How deploy this application

You need maven installed in your machine.

        mvn clean package -P prod docker:build

Run command below to know which images wish.

        docker images

The command below run application in docker

        docker run -p 80:8080 -t springio

Use Postman or Insomnia to make request on services.

        + Url available
          - localhost:8080/meteorologia?c=london
          - localhost:8080/meteorologia/lat/-22.9/log/-40.0/


Guys, thanks for opportunity.
