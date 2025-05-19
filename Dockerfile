# Use uma imagem oficial do Java 17 ou superior
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copie o jar compilado e demais arquivos necessários
COPY target/peerToPeer-0.0.1-SNAPSHOT.jar app.jar
COPY diretorio_compartilhado/ diretorio_compartilhado/
COPY vizinhos vizinhos

COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
