FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/vis-artifact.jar
COPY target/vis-artifact.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]