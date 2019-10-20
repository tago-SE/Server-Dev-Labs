This is a canvas drawer application allowing multiple users to connect to the server and  paint on the same canvas. It was made following "The Coding Train" and the original source code can be found here: https://github.com/CodingTrain/website 

# Build Setup

    docker build -t canvas-drawer .
    docker run -p 3000:3000 -d canvas-drawer

#End-points

    192.168.99.100:3000     (windows)