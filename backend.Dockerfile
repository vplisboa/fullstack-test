FROM maven:3-jdk-11
WORKDIR /app
ADD . .
RUN mvn clean install

FROM openjdk:11-slim
COPY --from=0 /app/target/weatherMusic-0.0.1-SNAPSHOT.jar /opt/app.jar
CMD ["java","-jar","/opt/app.jar"]
