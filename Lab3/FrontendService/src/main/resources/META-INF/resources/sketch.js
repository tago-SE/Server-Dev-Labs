var socket;

// Store the painting

function setup()
{
    createCanvas(400, 400);
    background(0);
    socket = io.connect('http://192.168.99.100:3000');
    // We make a named event called 'mouse' and write an
    // anonymous callback function
    socket.on('mouse',
        // When we receive data
        function(data) {
            console.log("Got: " + data.x + " " + data.y);
            //paintList.push(data);

            // Draw a blue circle
            fill(0,0,255);
            noStroke();
            ellipse(data.x, data.y, 20, 20);

        }
    );
}

function draw()
{
    // Nothing
}

function mouseDragged()
{
    // Draw some white circles
    fill(255);
    noStroke();
    ellipse(mouseX,mouseY,20,20);
    // Send the mouse coordinates
    sendmouse(mouseX,mouseY);
}

// Function for sending to the socket
function sendmouse(xpos, ypos)
{
    console.log("send: " + xpos + " " + ypos);
    var data = {
        x: xpos,
        y: ypos
    };
    // Send that object to the socket
    socket.emit('mouse', data);
}

function clear_image()
{
    location.reload();
    setup();
}

function upload_image(username) {
    /*
    var data = {
        username: username, // First test that saving username works
    }
    socket.emit('save', data);
    clear_image();
*/
}
