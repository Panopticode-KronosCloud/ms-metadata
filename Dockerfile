FROM eclipse-temurin:21.0.2_13-jre-alpine

LABEL org.opencontainers.image.source=https://github.com/Phortran/java-spring-boot-ms-template
LABEL org.opencontainers.image.description="Java Spring Boot microservice template"
LABEL org.opencontainers.image.licenses=GPL-3.0-or-later

WORKDIR /app
COPY build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]