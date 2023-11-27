FROM gradle:jdk17-focal as builder
COPY app app
WORKDIR /home/gradle/app
RUN ./gradlew --no-daemon build

FROM eclipse-temurin:latest
EXPOSE 8080
ENV KCPHELPER_HOME=/opt/kcphelper
COPY --from=builder /home/gradle/app/kcphelper/build/quarkus-app/ ${KCPHELPER_HOME}/
WORKDIR ${KCPHELPER_HOME}/
RUN mv quarkus-run.jar kcphelper-run.jar
CMD  java -jar kcphelper-run.jar
