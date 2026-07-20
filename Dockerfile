# Stage 1: Build with Maven + Java 21
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /build

COPY pom.xml .
COPY mvnw mvnw.cmd ./
COPY .mvn .mvn

# Download dependencies first (cached layer)
RUN ./mvnw dependency:go-offline -q

COPY src src

RUN ./mvnw package -q -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/target/swe-bench-verified-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
