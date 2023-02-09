FROM openjdk:8-jdk
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
RUN bash -c 'mkdir -pv /etc/{image/{logo,introduce,applicant,evaluator}}'
