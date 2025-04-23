FROM maven:3.9.9-sapmachine-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-slim

WORKDIR /app
COPY --from=build /app/target/journalApp-0.1.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/target/journalApp-0.1.jar"]
