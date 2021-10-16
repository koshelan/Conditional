FROM adoptopenjdk/openjdk11:x86_64-ubuntu-jdk-11.0.12_7-slim
EXPOSE 8080
ADD target/conditional-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]