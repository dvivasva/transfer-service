FROM openjdk:11.0-oracle
ARG JAR_FILE=target/transfer-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} transfer.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","/transfer.jar"]