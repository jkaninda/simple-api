# Build stage
FROM maven:3.8.7-amazoncorretto-17 AS MAVEN_TOOL_CHAIN
WORKDIR /tmp/
COPY pom.xml /tmp/pom.xml
COPY src /tmp/src/
RUN mvn clean package -B

FROM ubuntu:22.04
ARG JAR_FILE=target/*.jar
ENV VERSION="1.0"
LABEL author="Jonas Kaninda"
LABEL github="https://github.com/jkaninda/simple-api"
ARG USER_ID=1000
ENV USER_NAME=jkaninda
RUN apt-get update && apt-get -y  install wget openjdk-17-jdk

# Clear cache
RUN apt-get clean && rm -rf /var/lib/apt/lists/*
## Add User
RUN useradd $USER_NAME
RUN usermod -u ${USER_ID} ${USER_NAME}
RUN groupmod -g ${USER_ID} ${USER_NAME}

# Copy source from build satge
COPY --from=MAVEN_TOOL_CHAIN /tmp/$JAR_FILE /App/api.jar
COPY ./data /data

RUN chown -R ${USER_NAME}:${USER_NAME} /App && \
    chmod +x /App/api.jar

USER $USER_NAME
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/App/api.jar"]