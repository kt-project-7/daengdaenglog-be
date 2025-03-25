FROM amazoncorretto:17-alpine
COPY build/libs/guide-0.0.1-SNAPSHOT.jar guide.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "guide.jar"]