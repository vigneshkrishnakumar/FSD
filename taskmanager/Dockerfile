FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8090
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /taskmanager/lib
COPY ${DEPENDENCY}/META-INF /taskmanager/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /taskmanager
ENTRYPOINT ["java","-cp","taskmanager:taskmanager/lib/*","com.cts.capsule.taskmanager.TaskmanagerApplication"]