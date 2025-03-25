FROM amazoncorretto:17-alpine
COPY build/libs/memory-0.0.1-SNAPSHOT.jar memory.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "memory.jar"]