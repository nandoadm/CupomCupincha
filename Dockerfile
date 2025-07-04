FROM ubuntu:latest
LABEL authors="Nando"

# Usa uma imagem base leve com Java 17 instalado
FROM openjdk:17-jdk-slim

# Cria um volume temporário (usado pelo Spring para arquivos temporários)
VOLUME /tmp

# Copia o .jar gerado pelo Maven (ajuste o nome abaixo se necessário)
COPY target/Frontend-0.0.1-SNAPSHOT.jar app.jar

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]
