# How to dockerize a Spring boot application

https://spring.io/guides/gs/spring-boot-docker/ <--- USED THIS
https://www.baeldung.com/dockerizing-spring-boot-application
Example project
https://github.com/chargeahead/SpringBootDockerMaven/tree/master/src/main

# Java and Maven configuration 

Before starting make sure that JAVA_HOME and MAVEN_HOME has been installed
and configured properly (environment variables & path)
Environment variables, see: https://www.mkyong.com/maven/how-to-install-maven-in-windows/

        $ javac --version
        $ java -version
        $ mvn --version
        
# Setting Up the Maven Wrapper inside the project 
https://www.baeldung.com/maven-wrapper

Open the main project folder and run the command(s)

    $ mvn -N io.takari:maven:wrapper

After executing the goal, we'll have more files and directories in the project:
mvnw: it's an executable Unix shell script used in place of a fully installed Maven
mvnw.cmd: it's the Batch version of the above script
mvn: the hidden folder that holds the Maven Wrapper Java library and its properties file

# Run the following command to build and run, you can now access it using localhost:8080/users/all

     $ ./mvnw package && java -jar target/spring-docker-template-0.1.0.jar

Note that the command is mvnw.cmd for windows

# Install, build and push the docker file
     $ ./mvnw install dockerfile:build
     $ ./mvnw dockerfile:push