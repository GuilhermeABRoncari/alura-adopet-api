FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./mvnw clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /build/libs/adopet-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-DDATASOURCE_URL=jdbc:mysql://root:Lf94r7l2uVaPsJ0EVBGr@containers-us-west-113.railway.app:5851/railway -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=Lf94r7l2uVaPsJ0EVBGr", "-jar", "adopet-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
