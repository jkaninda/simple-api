FROM amazoncorretto:17-alpine3.19-jdk
ARG JAR_FILE=target/*.jar
COPY target/*.jar   /App/api.jar
COPY ./data /data

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/App/api.jar"]