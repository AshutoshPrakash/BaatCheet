package prakash.ashutosh.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class ChatActivity extends AppCompatActivity {

    public String you, friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle data = getIntent().getExtras();
        if(data==null){
            return;
        }
        you = data.getString("you");
        friend = data.getString("friend");

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(friend);
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setMovementMethod(new ScrollingMovementMethod());

        connectWebSocket();
    }

    private WebSocketClient mWebSocketClient;
    public void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://52.89.192.35:8080");
            mWebSocketClient = new WebSocketClient(uri,new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.i("Websocket", "Opened");
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("user", you);
                        mWebSocketClient.send(obj.toString());
                        showToast("Connection Established");
                    }catch (JSONException j){
                        showToast("Connection Problem");
                    }
                }

                @Override
                public void onMessage(String s) {
                    final String message = s;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = (TextView)findViewById(R.id.textView2);
                            try{
                                JSONObject  jsonObject = new JSONObject(message);
                                if(jsonObject.optString("error").toString().equals("yes"))
                                    showToast("Friend not connected to Server right now!");
                                else
                                    textView.setText(textView.getText() + "\n" + jsonObject.optString("you").toString() + ": "
                                            + jsonObject.optString("msg").toString());
                            }catch (JSONException j){
                                showToast("Wrong JSON format!");
                            }
                        }
                    });
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.i("Websocket", "Closed " + s);
                    showToast("Connection Closed");
                }

                @Override
                public void onError(Exception e) {
                    Log.i("Websocket", "Error " + e.getMessage());
                    showToast("Connection Error");
                }
            };
            mWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void handler(View arg0) {
        try{
            JSONObject obj = new JSONObject();
            obj.put("you", you);
            obj.put("friend",friend);
            EditText editText = (EditText)findViewById(R.id.editText3);
            obj.put("msg",editText.getText().toString());
            TextView textView = (TextView)findViewById(R.id.textView2);
            textView.setText(textView.getText() + "\n Me: " + editText.getText().toString());
            editText.setText("");

            mWebSocketClient.send(obj.toString());
        }catch (JSONException j){
            Log.i("JSON", "Exception while snding login request.");
        }
    }

    public void showToast(final String s){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
