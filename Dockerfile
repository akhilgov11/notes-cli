FROM eclipse-temurin:17

WORKDIR /app

COPY src src

RUN javac src/com/example/*.java

VOLUME ["/app/data"]

ENTRYPOINT ["java", "-cp", "src", "com.example.App"]
