FROM amazoncorretto:17-alpine
COPY build/libs/ai-0.0.1-SNAPSHOT.jar ai.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "ai.jar"]