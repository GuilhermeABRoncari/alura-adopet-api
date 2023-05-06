FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ../target/adopet-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-DDATASOURCE_URL=jdbc:mysql://root:Lf94r7l2uVaPsJ0EVBGr@containers-us-west-113.railway.app:5851/railway -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=Lf94r7l2uVaPsJ0EVBGr", "-jar", "adopet-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
