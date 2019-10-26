// This is just some code used to test some of the vertx services
// during the development phase 

console.log("hello!");

const WebSocket = require('ws');
var socket = new WebSocket('ws://localhost:5050');

// Open the socket
socket.onopen = function(event) {
  socket.send('Ping');

  socket.onmessage = function(event) {
      console.log('Client received a message', event.data.toString());
  };

  // Listen for socket closes
  socket.onclose = function(event) {
    //console.log('Client notified socket has closed', event);
  };
}

/*
const fetch = require('node-fetch');

const request = require('request-promise');

let formData = {
    "id" : "",
    "username" : "Tiago",
    "label" : null,
    "timestamp" : null,
    "name" : "bar",
    "type" : "bar",
    "dataPoints" : [ {
      "x" : 1,
      "y" : 6
    }, {
      "x" : 2,
      "y" : 7
    }, {
      "x" : 3,
      "y" : 8
    } ]
};
request.post('http://localhost:7070/api/diagrams', {formData, json: true})
.then(function (json) {
    console.log(json);
})
.catch(function (err) {
    console.log(err);
}); 


fetch(`http://localhost:7070/api/diagrams/get/5db07cfd5e189c7a093bf920`)
    .then(res => res.json())
    .then(json => {
        console.log(json);
        console.log("id=" + json.id);
        console.log("Label=" + json.label);
        console.log("timestamp=" + json.timestamp);
        console.log("name=" + json.name);
        console.log("type=" + json.type);
        console.log("dp=" + json.dataPoints);
    }).catch(err => {
        console.log(err);
    });
*/