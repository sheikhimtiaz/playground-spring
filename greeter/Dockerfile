FROM adoptopenjdk/openjdk11:alpine-jre
ARG WAR_FILE=./target/*.war

COPY ${WAR_FILE} webapp.war

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "webapp.war"]