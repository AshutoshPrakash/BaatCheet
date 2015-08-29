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
