package com.example.activitylifecycle;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button count;
    TextView text;
    EditText editText;
    private int mcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count= findViewById(R.id.button);
        text= findViewById(R.id.textView);
        editText= findViewById(R.id.edittext);

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increament();
            }
        });
        if (savedInstanceState != null) {
            mcount= savedInstanceState.getInt("count");
            text.setText(String.valueOf(mcount));
        }
    }

    public void increament(){
        mcount++;
        text.setText(String.valueOf(mcount));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count",mcount);
    }


}
