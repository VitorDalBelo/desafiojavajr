# Use uma imagem base do JDK 22 (Java Development Kit)
FROM eclipse-temurin:22-jdk-alpine

# Define o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo JAR da aplicação para o diretório de trabalho
COPY target/crudjava-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 para acessar a aplicação no container
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
