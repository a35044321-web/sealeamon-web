# 第一階段：使用 Maven 進行打包 (改用 Temurin 來源)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 第二階段：執行 Java 程式 (改用最穩定的 Eclipse Temurin 映像檔)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# 從 build 階段將產出的 jar 檔複製過來
COPY --from=build /app/target/*.jar app.jar

# 開放 Spring Boot 預設的 8080 埠號
EXPOSE 8080

# 啟動指令
ENTRYPOINT ["java", "-jar", "app.jar"]