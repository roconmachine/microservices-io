FROM openjdk:17-alpine
EXPOSE 8888
ADD target/config-server-0.0.1.jar config-server-0.0.1.jar
ENTRYPOINT ["java","-jar","/config-server-0.0.1.jar"]