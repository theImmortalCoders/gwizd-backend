FROM openjdk:17
COPY target/gwizd_backend-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","Gwizd-0.0.1-SNAPSHOT.jar"]