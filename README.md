# SPRING BOOT TRUEFX.COM PARSER

## PROJECT DESCRIPTION:

This project consumes currency rates from http://webrates.truefx.com/rates/connect.html
and publish json output with via websocket. The margin of delay can be configured in application.properties for each currency rate pair.

## USAGE:

To start you need run application using maven plugin
```
mvn spring-boot:run

Open http://localhost:8080/ 
By default client is subscribed to /topic/aud.usd, /topic/eur.usd, /topic/usd.jpy

