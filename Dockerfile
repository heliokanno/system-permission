FROM openjdk:17-jdk-slim-buster
RUN mkdir /app

COPY build/libs/system-permission-*.jar /app/system-permission.jar

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/system-permission.jar"]
