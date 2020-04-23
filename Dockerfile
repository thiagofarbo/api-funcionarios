FROM openjdk:8
MAINTAINER Thiago Emidio <thiagofarbo@gmail.com>
ADD target/*.jar api-funcionarios.jar
# EXPOSE 8090
#set a health check
HEALTHCHECK --interval=5s \
            --timeout=5s \
            CMD curl -f http://127.0.0.1:8095 || exit 1
ENTRYPOINT ["java", "-jar", "api-funcionarios.jar"]