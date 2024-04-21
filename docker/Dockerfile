# Buil Stage
FROM maven:3.8.7-amazoncorretto-17 AS MAVEN_TOOL_CHAIN
WORKDIR /tmp/
COPY pom.xml /tmp/pom.xml
COPY src /tmp/src/
RUN mvn clean package -B

FROM amazoncorretto:17-alpine3.19-jdk
ENV VERSION="1.0"
LABEL author="Jonas Kaninda"
LABEL github="https://github.com/jkaninda/simple-api"
ARG JAR_FILE=target/*.jar
# Copy from build stage
COPY --from=MAVEN_TOOL_CHAIN /tmp/$JAR_FILE /App/api.jar
COPY ./data /data
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/App/api.jar"]