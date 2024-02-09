FROM openjdk:17
LABEL authors="Bobrus Alexander"

WORKDIR /java
COPY . /java

ENTRYPOINT ["java", "-jar", "target/cloneVK-0.0.1-SNAPSHOT.jar"]