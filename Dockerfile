#FROM openjdk:11
#EXPOSE 8084
#WORKDIR /app
#
## Copy maven executable to the image
#COPY mvnw .
#COPY .mvn .mvn
#
## Copy the pom.xml file
#COPY pom.xml .
#
## Copy the project source
#COPY ./src ./src
#COPY ./pom.xml ./pom.xml
#
#RUN chmod 755 /app/mvnw
#
#RUN ./mvnw dependency:go-offline -B
#
#RUN ./mvnw package
#RUN ls -al
#ENTRYPOINT ["java","-jar","target/schoolsystem-0.0.1-SNAPSHOT.jar"]

FROM maven:3-jdk-11
WORKDIR /app
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run