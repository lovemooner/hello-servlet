
function asyncRequest(){
   console.log("test async servlet");
   var httpRequest = new XMLHttpRequest();
   httpRequest.open('GET', '/servlet/simpleAsync?t='+new Date(), true);
   httpRequest.send();

   httpRequest.onreadystatechange = function () {
      if (httpRequest.readyState == 4 && httpRequest.status == 200) {
          var json = httpRequest.responseText;
          console.log(json);
          var contentElement = document.getElementById("content");
          contentElement.innerHTML = json + contentElement.innerHTML;
      }
  };
}


function webSocketRequest(){

    var ws = new WebSocket("ws://localhost:8080/servlet/simpleAsync");
    ws.onopen = function(evt) {
      console.log("Connection open ...");
      ws.send("Hello WebSockets!");
    };

    ws.onmessage = function(evt) {
      console.log( "Received Message: " + evt.data);
      ws.close();
    };

    ws.onclose = function(evt) {
      console.log("Connection closed.");
    };
}
