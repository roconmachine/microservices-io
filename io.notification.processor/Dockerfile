FROM openjdk:17-alpine
EXPOSE 8089
COPY target/io.notification.processor-0.0.1.jar io.notification.processor-0.0.1.jar
ENTRYPOINT ["java","-jar","/io.notification.processor-0.0.1.jar"]