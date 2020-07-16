# MAVEN REPOSITORY
FROM maven:3.6-jdk-14 as builder
WORKDIR /workspace

#copy pom.xml and download dependecies
COPY pom.xml .
RUN mvn dependency:go-offline

FROM builder as prepare
WORKDIR /workspace
COPY src src

#test stage
FROM prepare AS test
WORKDIR /workspace
RUN mvn test

#build stage
FROM prepare AS build
WORKDIR /workspace
RUN mvn package
RUN java -Djarmode=layertools -jar target/*.jar extract

FROM gcr.io/distroless/java:11
WORKDIR /workspace
ENV SPRING_PROFILES_ACTIVE=

COPY --from=build workspace/dependencies/ ./
COPY --from=build workspace/spring-boot-loader/ ./
COPY --from=build workspace/snapshot-dependencies/ ./
COPY --from=build workspace/application/ ./
USER 1000

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
