FROM maven:3.9.6-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/Instagram-Clone.jar Instagram.jar
EXPOSE 5050
ENTRYPOINT ["java", "-jar", "instagram.jar"]

