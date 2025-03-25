FROM amazoncorretto:17-alpine
COPY build/libs/pet-0.0.1-SNAPSHOT.jar pet.jar

ENV TZ Asia/Seoul
ARG ENV

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=dev", "pet.jar"]