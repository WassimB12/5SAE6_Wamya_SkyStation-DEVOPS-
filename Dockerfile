FROM openjdk:8
EXPOSE 8089
ADD target/gestion-station-ski-1.0.jar skistationDocker.jar
ENTRYPOINT ["java", "-jar", "skistationDocker.jar"]