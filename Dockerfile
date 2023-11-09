<<<<<<< HEAD
FROM openjdk:8
EXPOSE 8089
ADD target/gestion-station-ski-1.0.jar skistationDocker.jar
ENTRYPOINT ["java", "-jar", "skistationDocker.jar"]
=======
FROM maven:3.6.3-jdk-8

WORKDIR /opt/app/

COPY . .

COPY target/gestion-station-ski-1.0.jar /opt/app/app.jar

EXPOSE 8089

ENTRYPOINT ["java","-jar","app.jar"]
>>>>>>> 842a55ac3409a3f0e26afb78f906901e59f5e880
