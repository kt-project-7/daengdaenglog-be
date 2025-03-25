FROM amazoncorretto:17-alpine
COPY build/libs/pbti-0.0.1-SNAPSHOT.jar pbti.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "pbti.jar"]