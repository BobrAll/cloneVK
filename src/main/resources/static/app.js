const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/messenger'
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', (greeting) => {
        let message = JSON.parse(greeting.body);
        showGreeting(message.sender + ": " + message.content);
    });
};

function sendName() {
    stompClient.publish({
        destination: "/chat/message",
        body: JSON.stringify({'sender': $("#sender").val(), 'content': $("#message").val()})
    });
}

function showGreeting(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#send" ).click(() => sendName());
});

stompClient.activate();