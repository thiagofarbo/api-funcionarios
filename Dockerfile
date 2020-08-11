FROM openjdk:8
MAINTAINER Thiago Emidio <thiagofarbo@gmail.com>

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /deployments/libs/

RUN jar -xf ./*.jar

EXPOSE 8090

#set a health check
HEALTHCHECK --interval=5s --timeout=3s CMD curl --fail http://localhost:8090/pools || exit 1

ENTRYPOINT ["java", "-jar", "api-funcionarios.jar"]