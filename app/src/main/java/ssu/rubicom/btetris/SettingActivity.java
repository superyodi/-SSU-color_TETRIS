package ssu.rubicom.btetris;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private EditText editText_IP;
    private EditText editText_PORT;
    private Button button_cancel, button_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        editText_IP = (EditText) findViewById(R.id.editText_IP);
        editText_PORT = (EditText) findViewById(R.id.editText_Port);
        button_confirm = (Button) findViewById(R.id.button_confirm);
        button_cancel = (Button) findViewById(R.id.button_cancel);

        button_confirm.setOnClickListener(OnClickListener);
        button_cancel.setOnClickListener(OnClickListener);


    }

    public View.OnClickListener OnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.button_confirm:
                    Intent send_intent = new Intent();
                    String send_msg = "IP: "+editText_IP.getText().toString()+" , Port:"+editText_PORT.getText().toString();
                    //Toast.makeText(SettingActivity.this, send_msg, Toast.LENGTH_SHORT).show();
                    send_intent.putExtra("result", send_msg);
                    setResult(RESULT_OK, send_intent);
                    finish();
                    break;
                case R.id.button_cancel:
                    finish();
                    break;

            }
        }
    };
}
