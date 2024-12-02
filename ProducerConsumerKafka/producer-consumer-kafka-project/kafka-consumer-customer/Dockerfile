FROM openjdk:17
COPY target/kafka-consumer-customer-0.0.1-SNAPSHOT.jar docker-consumer-customer.jar
EXPOSE 9090
ENTRYPOINT ["java" , "-jar" , "/docker-consumer-customer.jar"]