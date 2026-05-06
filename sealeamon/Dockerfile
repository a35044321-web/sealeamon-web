# 使用 Java 17 作為基底
FROM maven:3.8.4-openjdk-17-slim AS build
COPY . .
# 編譯專案
RUN mvn clean package -DskipTests

# 執行階段
FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar
# 開放 8080 埠號 (Spring Boot 預設)
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]