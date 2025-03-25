FROM amazoncorretto:17-alpine
COPY build/libs/diary-0.0.1-SNAPSHOT.jar diary.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "diary.jar"]