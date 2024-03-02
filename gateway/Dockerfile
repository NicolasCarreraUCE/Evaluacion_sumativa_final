FROM eclipse-temurin:17.0.10_7-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY build/libs/gateway.jar app.jar

CMD ["java","-jar","app.jar"]