This is a microservice that manages user resources, such as login, registration, user detail information, etc. 
# Build Setup

Open the project folder and execute the following commands 
   
    mvn -N io.takari:maven:wrapper
Creates maven build files if not already present. 
    
    ./mvnw package && java -jar target/usersmicroservice-0.1.0.jar
    
Runs the application locally

    ./mvnw install dockerfile:build
    
Creates a docker image 

    docker images
    
Checks if the docker image was created

    winpty docker run -it -p 9091:8080 -t springio/usersmicroservice
    
Runs the docker image. Notice that 'winpty' command is only needed if executed from windows.

    192.168.99.100:9091/users   (windows)
    localhost:9091/users        (linux)
    
End-points