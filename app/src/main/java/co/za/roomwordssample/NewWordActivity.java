package co.za.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {
    public static String EXTRA_REPLY = "co.za.roomwordssample.REPLY";
    private EditText mEditWordView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);
        button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                if(!TextUtils.isEmpty(mEditWordView.getText())){
                    replyIntent.putExtra(EXTRA_REPLY, mEditWordView.getText().toString());
                    setResult(Activity.RESULT_OK, replyIntent);

                }
                else{
                    setResult(RESULT_CANCELED, replyIntent);
                }

                finish();
            }
        });
    }
}
