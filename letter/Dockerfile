FROM amazoncorretto:17-alpine
COPY build/libs/letter-0.0.1-SNAPSHOT.jar letter.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "letter.jar"]