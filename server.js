<<<<<<< HEAD
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
=======
/*var http = require('http');
var port = 9000;

var server = http.createServer(function(req,res){
	res.writeHead(200,{'Content-Type':'text/plain'});
	res.end('Hello World!\n');
});

server.listen(port);

console.log('Listening on port 9000!');*/

var net = require('net');
var HOST = '127.0.0.1';
var PORT = 3000;
net.createServer(function(sock) {
    
    console.log('CONNECTED: ' + sock.remoteAddress +':'+ sock.remotePort);

    sock.on('data', function(data) {
        
        console.log(data.toString());
        //console.log(data);
        //console.log("hello");
    });

    sock.on('close', function(data) {
        console.log('CLOSED!');
    });
    
}).listen(PORT, HOST);
console.log('Server listening on ' + HOST +':'+ PORT);
>>>>>>> 63c2a145df77a3fbbaa13771bbbd0c8226736446
