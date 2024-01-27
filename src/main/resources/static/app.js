const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/messenger'
});

function connect() {
    let sender_id = $("#sender").val()

    stompClient.subscribe(`/user/${sender_id}/queue/messages`, (data) => {
        let message = JSON.parse(data.body);
        showMessage(message.senderId, message.content);
    });
};

function sendMessage() {
    let sender_id = $("#sender").val()
    let recipient_id = $("#recipient").val()
    let message = $("#message").val()

    stompClient.publish({
        destination: "/chat/message",
        body: JSON.stringify({
            'senderId': sender_id,
            'recipientId': recipient_id,
            'content': message})
    });

    showMessage(sender_id, message)
}

function showMessage(id, content) {
    $("#messages").append("<tr><td>" + id + ": " + content + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#send").click(() => sendMessage());
});

stompClient.activate();