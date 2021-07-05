FROM openjdk:11-jre-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bootcamp-meli-frescos.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/bootcamp-meli-frescos.jar"]