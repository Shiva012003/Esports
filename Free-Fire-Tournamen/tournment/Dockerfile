FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

EXPOSE 8081

CMD ["java","-jar","target/tournment-0.0.1-SNAPSHOT.jar"]
