FROM openjdk:21-oracle
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "sleep 10 && java -jar target/sensor-api-0.0.1-SNAPSHOT.jar"]
