FROM openjdk:8u212-jdk-alpine

# Download Gradle before copying source to cache it
COPY *.gradle gradle.* gradlew /src/
COPY gradle /src/gradle
WORKDIR /src
RUN ./gradlew --version

COPY . .
RUN ./gradlew run
