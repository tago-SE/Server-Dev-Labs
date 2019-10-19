This is a micro service for performing CRUD operations on  Post Table.

# Build Setup

Open the project folder and execute the following commands 
   
    mvn -N io.takari:maven:wrapper
Creates maven build files if not already present. 
    
    ./mvnw package && java -jar target/postsmicroservice-0.1.0.jar
    
Runs the application locally

    ./mvnw install dockerfile:build
    
Creates a docker image 

    docker images
    
Checks if the docker image was created

    winpty docker run -it -p 9093:8080 -t springio/postsmicroservice
    
Runs the docker image. Notice that 'winpty' command is only needed if executed from windows.

# End points
    192.168.99.100:9093/posts   (windows)
    localhost:9093/posts        (linux)