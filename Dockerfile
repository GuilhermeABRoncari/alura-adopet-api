FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build ../target/adopet-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENV DATASOURCE_URL=jdbc:mysql://containers-us-west-113.railway.app:5851/railway
ENV DATASOURCE_USERNAME=root
ENV DATASOURCE_PASSWORD=Lf94r7l2uVaPsJ0EVBGr

ENTRYPOINT ["java", "-jar", "adopet-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
