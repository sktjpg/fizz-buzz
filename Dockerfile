FROM gradle:8.5-jdk21 AS build
WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

ENV GRADLE_OPTS="-Xmx512m"
RUN gradle build --no-daemon --parallel -x test

FROM openjdk:21-slim
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-Xmx512m", "-jar", "app.jar"]
