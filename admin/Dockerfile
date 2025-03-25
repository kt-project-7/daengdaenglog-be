FROM amazoncorretto:17-alpine
COPY build/libs/admin-0.0.1-SNAPSHOT.jar admin.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "admin.jar"]