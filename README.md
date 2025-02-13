# Grundlegendes
Die implementierte Spring Boot Applikation publiziert Rest Endpoints für die Client Entität.
In Grundzügen ist die Dokumentation der API per OpenAPI durchgeführt und kann unter [swagger-ui](http://localhost:8080/api/swagger-ui/index.html) als UI abgefragt werden.
Eine Dokumentation ohne grafische Aufbereitung ist unter [openapi](http://localhost:8080/api/openapi) abzurufen.

# Projektstruktur 
Da es sich hierbei um ein kleines Projekt handelt, ist die Codestruktur nach Domäne, nicht bspw. nach einer 3-Tier Architektur gebaut. 
Unter dem /test-Directory sind anstelle von Unit Tests eine kleine Einheit Integrationtests enthalten. 
Diese würden für größere Applikation in ein /integrationtest Directory ausgelagert werden. 

# Bootstraping
## per Maven
### Bau der Applikation
`mvn clean install`
### Start der Applikation
`mvn spring-boot:run`

## per Docker
### Bauen eines Images
`docker build -t=openapi-test .`
### Starten des Images
`docker run -p 8080:8080 openapi-test`


# Nicht berücksichtige Themen
- Einführung und Mapping von Entity<->DTO auf Rudimentäres beschränkt
- Error Handling sowohl fachlicher als auch technischer Natur nicht berücksichtigt 
- Keine Fachliche Validierungen (Doppelter clientId, E-Mail Regex etc.)
- HTTP Responses erfüllen aktuell nur den Happy Path
- CI Pipeline deployt kein Docker Image in einen Docker Hub
- Statische Codeanalyse mit rudimentären Tools der Github Actions durchgeführt, da bspw. kein SonarQube aktuell verwendbar
- Keine Jenkins Pipeline, da keine Jenkins Instanz verfügbar
- ... (weitere Themen)
