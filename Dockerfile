FROM openjdk:11.0.5-jre-slim

COPY ./target/matrix-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch matrix-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","matrix-0.0.1-SNAPSHOT.jar"]