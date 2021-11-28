FROM openjdk:11-jdk-oraclelinux8
#RUN addgroup -S spring && adduser -S spring -G spring
RUN groupadd spring && useradd -g spring spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]