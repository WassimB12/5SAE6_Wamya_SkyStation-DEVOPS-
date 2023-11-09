FROM openjdk:8
WORKDIR /opt/app/

COPY . .

COPY target/gestion-station-ski-1.0.jar /opt/app/app.jar

EXPOSE 8089

ENTRYPOINT ["java","-jar","app.jar"]
