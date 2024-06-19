# Usando uma imagem oficial do Maven para construir a aplicação
FROM maven:3.8.4-openjdk-17 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o código fonte e construa a aplicação
COPY src ./src
RUN mvn package -DskipTests

# Usando uma imagem oficial do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR do estágio de build para o estágio final
COPY --from=build /app/target/*.jar app.jar

# Defina o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
