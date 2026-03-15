FROM amazoncorretto:21-alpine-jdk AS builder

WORKDIR /build

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew
RUN ./gradlew dependencies

COPY src src

RUN ./gradlew build -x test

FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY --from=builder /build/build/libs/authlab-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]