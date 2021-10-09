FROM adoptopenjdk/openjdk11:alpine-jre
COPY ./build/libs/githubapi-0.0.1-SNAPSHOT.jar /githubapi.jar
EXPOSE 8080
RUN java -jar /githubapi.jar