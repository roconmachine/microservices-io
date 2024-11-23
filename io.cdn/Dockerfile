FROM openjdk:17-alpine
EXPOSE 8085
#COPY ../location /location
COPY target/io.cdn-0.0.1.jar io.cdn-0.0.1.jar
ENTRYPOINT ["java","-jar","/io.cdn-0.0.1.jar"]