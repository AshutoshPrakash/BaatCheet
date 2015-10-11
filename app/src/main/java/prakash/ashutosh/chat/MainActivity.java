package prakash.ashutosh.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button button;
    public EditText you, friend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friend = (EditText)findViewById(R.id.editText2);
        you = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
    }

    public void handler(View arg0) {
        if(you.getText().toString().equals("") ||friend.getText().toString().equals(""))
            showToast("Can't be empty!");
        else{
            Intent i = new Intent(this, ChatActivity.class);
            i.putExtra("you",you.getText().toString());
            i.putExtra("friend",friend.getText().toString());
            startActivity(i);
        }
    }

    public void showToast(final String s){
        runOnUiThread(new Runnable() {
            public void run()
            {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
