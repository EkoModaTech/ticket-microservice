# SET DOCKER_BUILDKIT=1 to avoid redownloading maven dependencies
FROM maven:latest as MAVEN_BUILD
COPY ./ ./
RUN --mount=type=cache,target=/root/.m2 mvn clean package
RUN mv target/*.jar target/ticket.jar

FROM eclipse-temurin:latest
WORKDIR /app
COPY --from=MAVEN_BUILD /target/ticket.jar /app
COPY --from=MAVEN_BUILD /src/main/resources/*.yml /app
EXPOSE 8080
CMD ["java", "-jar", "ticket.jar"]
