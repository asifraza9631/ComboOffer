FROM openjdk:17-jdk-slim-buster

COPY build/libs/ComboOffer-0.0.1-SNAPSHOT.jar ComboOffer-0.0.1-SNAPSHOT.jar

ENTRYPOINT java -jar ComboOffer-0.0.1-SNAPSHOT.jar