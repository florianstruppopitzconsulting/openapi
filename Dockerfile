FROM amazoncorretto:21.0.4-alpine3.18
COPY target/openapi-0.0.1-SNAPSHOT.jar openapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/openapi-0.0.1-SNAPSHOT.jar"]

