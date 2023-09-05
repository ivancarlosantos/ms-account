FROM maven:3.8.5-openjdk-17 AS build

COPY /src /app/src

COPY /pom.xml /app

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:22-ea-12-jdk-slim

LABEL key="core.ics"

WORKDIR /usr/src/app

COPY --from=build /app/target/*.jar account.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "account.jar"]