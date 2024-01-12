const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/messenger'
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/user/1/queue/messages', (greeting) => {
        let message = JSON.parse(greeting.body);
        showGreeting(message.senderId + ": " + message.content);
    });
};

function sendName() {
    stompClient.publish({
        destination: "/chat/message",
        body: JSON.stringify({
            'senderId': $("#sender").val(),
            'recipientId': $("#recipient").val(),
            'content': $("#message").val()})
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