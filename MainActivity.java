package prakash.ashutosh.baatcheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public Button button;
    public EditText editText;
    public String ip = "10.0.2.2";
    public int port = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        button.setOnClickListener(listener);

        /*Thread thread = new Thread() {
            @Override
            public void run() {
                Receive r = new Receive(ip,port);
                r.execute();
            }
        };

        thread.start();*/

    }

    OnClickListener listener = new OnClickListener(){
        @Override
        public void onClick(View arg0) {
            Sending s = new Sending(editText.getText().toString(),ip,port);
            s.execute();
        }
    };
}
