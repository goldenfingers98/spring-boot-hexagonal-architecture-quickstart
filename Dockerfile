#
# Build stage
#
FROM maven:3.8-eclipse-temurin-11-alpine AS build
COPY . /home/app
WORKDIR /home/app
RUN mvn clean install -DskipTests
#
# Package stage
#
FROM bellsoft/liberica-openjdk-alpine:11.0.15
RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password goldenfigners98
USER goldenfingers98
COPY --from=build /home/app/ms-bootloader/target/ms-bootloader-1.0-SNAPSHOT.jar /opt/ms-bootloader.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/ms-bootloader.jar"]