FROM maven:3.6.3-jdk-8

WORKDIR /opt/app/

COPY . .

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY target/gestion-station-ski-1.0.jar /opt/app/app.jar

EXPOSE 8089

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
