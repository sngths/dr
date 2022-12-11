const socket = new WebSocket("ws://localhost:8080/my-websocket-endpoint");

socket.onopen = function() {
    console.log("WebSocket opened");
};

const messageDisplay = document.getElementById('message-display');

// 当收到消息时，在消息展示区中添加一条消息
socket.onmessage = function(event) {
    const message = event.data;
    const p = document.createElement('p');
    p.style.color = 'green';
    p.innerText = message;
    p.style.gap = '0';
    p.style.margin = '0';
    p.style.padding = '0';
    messageDisplay.appendChild(p);
};

socket.onerror = function(error) {
    console.error("WebSocket error: " + error);
};

const sendButton = document.getElementById("send");

sendButton.addEventListener("click", function() {
    const message = document.getElementById("message").value;
    socket.send(message);
    const p = document.createElement('p');
    p.style.color = 'gray';
    p.innerText = message;
    p.style.gap = '0';
    p.style.margin = '0';
    p.style.padding = '0';
    messageDisplay.appendChild(p);
});

//显示连接状态
const connectionStatus = document.getElementById("connection-status");
socket.onopen = function() {
    connectionStatus.classList.add("connected");
};
socket.onclose = function() {
    connectionStatus.classList.remove("connected");
    connectionStatus.classList.add("disconnected");
};