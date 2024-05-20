FROM amazoncorretto:21.0.3-alpine3.19
EXPOSE 8080
COPY build/libs/bookstore-0.0.1-SNAPSHOT.jar /bookstore.jar
ENTRYPOINT ["java","-jar","/bookstore.jar"]
