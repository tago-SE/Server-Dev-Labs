# How to dockerize a Spring boot application

https://spring.io/guides/gs/spring-boot-docker/

https://www.baeldung.com/dockerizing-spring-boot-application

Example project
https://github.com/chargeahead/SpringBootDockerMaven/tree/master/src/main

# Java and Maven configuration 

Before starting make sure that JAVA_HOME and MAVEN_HOME has been installed
and configured properly (see: https://www.mkyong.com/maven/how-to-install-maven-in-windows/)

Execute these commands to verify that everything is in order

    javac --version
    java -version
    mvn --version
        
# Setting Up the Maven Wrapper inside the project 
https://www.baeldung.com/maven-wrapper

    mvn -N io.takari:maven:wrapper

After executing the goal, we'll have more files and directories in the project:

<br/>mvnw: it's an executable Unix shell script used in place of a fully installed Maven
<br/>mvnw.cmd: it's the Batch version of the above script
<br/>mvn: the hidden folder that holds the Maven Wrapper Java library and its properties file

# Run the springboot application

     ./mvnw package && java -jar target/spring-docker-template-0.1.0.jar
     
Note that the command is mvnw.cmd for window  
After the application has started you can access it using localhost:8080/users/all for example

# Install, build and push the docker file
     ./mvnw install dockerfile:build
     ./mvnw dockerfile:push (optional)
     
check if the docker image was created

    docker images

Spawn a container running the created docker image

    docker run -it -p 9090:8080 -t springio/spring-docker-template

Append 'winpty' if the input device is not a TTY.
