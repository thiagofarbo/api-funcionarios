FROM openjdk:8
MAINTAINER Thiago Emidio
ADD target/api-funcionarios-0.0.1.jar api-funcionarios.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "api-funcionarios.jar"]