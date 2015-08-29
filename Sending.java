package prakash.ashutosh.baatcheet;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by ashutosh on 21/8/15.
 */
public class Sending extends AsyncTask<Void, Void, Void> {

    String toSend;
    String ip ;
    int port;
    static Socket sock = null;

    String rec;
    byte[] buffer = new byte[1024];
    int bytesRead;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
    Socket socket = null;

    Sending(String data,String s,int i){
        toSend = data;
        port = i;
        ip = s;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        System.out.println("Connecting...");
        try {
            if(sock == null)sock = new Socket(ip, port);
            System.out.println("Connection Established !!");

            DataOutputStream outStream = new DataOutputStream(sock.getOutputStream());
            outStream.writeChars(toSend);
            outStream.flush();
            System.out.println("Msg Sent!");

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            while ((bytesRead = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                rec = byteArrayOutputStream.toString("UTF-8");
                System.out.println("Got : " + rec);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connection Error!");
        }
        return null;
    }
}
