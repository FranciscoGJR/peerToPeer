FROM openjdk:8-jdk-slim
WORKDIR /app
COPY pom.xml .
COPY vizinhos /app/vizinhos
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean install
ENTRYPOINT ["java", "-jar", "target/eachare.jar"]
CMD ["/app/vizinhos", "/app/compartilhar"]
