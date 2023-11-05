FROM maven:3.6.3-jdk-8

WORKDIR /opt/app/

ADD target/gestion-station-ski-1.0.jar .

COPY gestion-station-ski-1.0.jar app.jar

EXPOSE 8089

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
