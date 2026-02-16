FROM eclipse-temurin:21.0.2_13-jre-alpine

LABEL org.opencontainers.image.source=https://github.com/Phortran/metadata-microservice
LABEL org.opencontainers.image.description="Metadata microservice"
LABEL org.opencontainers.image.licenses=GPL-3.0-or-later

WORKDIR /app
# Copy jar
COPY build/libs/*.jar app.jar
# Copy entrypoint
COPY src/main/docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/entrypoint.sh"]