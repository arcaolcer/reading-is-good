FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
COPY target/ /opt/apps/
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/opt/apps/reading-is-good-1.0.1.jar"]
