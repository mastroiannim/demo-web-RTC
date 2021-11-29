//connecting to our signaling server 
if(window.location.href.split("//:")[0] == 'https')
    var conn = new WebSocket('wss://' + window.location.href.split("/")[2] + '/socket');
else 
    var conn = new WebSocket('ws://' + window.location.href.split("/")[2] + '/socket');

conn.onopen = function() {
    logMe("Connected to the signaling server");
    initialize();
};

conn.onmessage = function(msg) {
    logMe("Got message" + msg.data);
    var content = JSON.parse(msg.data);
    var data = content.data;
    switch (content.event) {
    // when somebody wants to call us
    case "offer":
        handleOffer(data);
        break;
    case "answer":
        handleAnswer(data);
        break;
    // when a remote peer sends an ice candidate to us
    case "candidate":
        handleCandidate(data);
        break;
    default:
        break;
    }
};

function send(message) {
    conn.send(JSON.stringify(message));
}

var peerConnection;
var dataChannel;
var input = document.getElementById("messageInput");

function initialize() {
    var configuration = null;

    peerConnection = new RTCPeerConnection(configuration);

    // Setup ice handling
    peerConnection.onicecandidate = function(event) {
        if (event.candidate) {
            logMe(JSON.stringify(event.candidate))
            send({
                event : "candidate",
                data : event.candidate
            });
        }
    };

    // creating data channel
    dataChannel = peerConnection.createDataChannel("dataChannel", {
        reliable : true
    });

    dataChannel.onerror = function(error) {
        logMe("Error occured on datachannel:" +  error);
    };

    // when we receive a message from the other peer, printing it on the console
    dataChannel.onmessage = function(event) {
        logMe("message:" + event.data);
    };

    dataChannel.onclose = function() {
        logMe("data channel is closed");
    };

    dataChannel.onopen = function() {
        dataChannel.send('Hi back!');
        logMe("data channel is onopend");
    };
  
  	peerConnection.ondatachannel = function (event) {
        dataChannel = event.channel;
  	};
    
}

function createOffer() {
    peerConnection.createOffer()
        .then(function(offer) {
            //console.log(offer);
            return peerConnection.setLocalDescription(offer);
        })
        .then(function() {
            //console.log(peerConnection.localDescription)
            send({
                event : "offer",
                data : peerConnection.localDescription
            });
        })
        .catch(function(reason) {
            // An error occurred, so handle the failure to connect
            console.log(reason);
            alert("Error creating an offer");
        });
}

function handleOffer(offer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(offer))
    .then(function(){
        // create and send an answer to an offer
        peerConnection.createAnswer()
        .then(function(answer) {
            return peerConnection.setLocalDescription(answer);
        })
        .then(function() {
            // Send the answer to the remote peer through the signaling server.
            send({
                event : "answer",
                data : peerConnection.localDescription
            });
        })
        .catch(function(reason) {
            // An error occurred, so handle the failure to connect
            logMe(reason);
            //alert("Error creating an answer");
        });
    });
};

function handleCandidate(candidate) {
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
};

function handleAnswer(answer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
    console.log("connection established successfully!!");
};

function sendMessage() {
    dataChannel.send(input.value);
    input.value = "";
}

function logMe(info){
    console.log(info);
    document.getElementById("logMe").innerHTML += "<p>" + info + "</p>";
}