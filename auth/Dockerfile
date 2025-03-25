FROM amazoncorretto:17-alpine
COPY build/libs/auth-0.0.1-SNAPSHOT.jar auth.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "auth.jar"]