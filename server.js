var ws = require("nodejs-websocket");
 
var userSocks = {};

var server = ws.createServer(function (conn) {

    console.log("New connection");
  
    conn.on("text", function (str) {
        console.log("Received "+str);
        try{
            var recv = JSON.parse(str);
            if(recv.user!=undefined){
            	userSocks[recv.user] = conn;
            }else{
            	for (var key in userSocks) {
            		if(key==recv.friend)
            			userSocks[key].sendText(str);
            		else conn.sendText(JSON.stringify({error:"yes"}));
				}
            }
        }catch(v){
        	conn.sendText(JSON.stringify({error:"yes"}));
        }
    });

    conn.on("close", function (code, reason) {
        console.log("Connection closed");
    });
    conn.on("error", function (code, reason) {
        console.log("Connection error");
    });
}).listen(8080);