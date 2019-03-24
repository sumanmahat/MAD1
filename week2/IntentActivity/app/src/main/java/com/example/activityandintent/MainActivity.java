package com.example.activityandintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    private int mCount = 0;
    private TextView mShowCount;

    public static final int TEXT_REQUEST = 1;

    public static final String EXTRA_MESSAGE =
            "com.example.admin.learningthings.extra.MESSAGE";

    private EditText mMessageEditText;
    Button Tost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        Tost= findViewById(R.id.button_toast);



    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
    }

    public void launchSecondActivity(View view){
        Log.d(LOG_TAG, "launchSecondActivity: Button checked");
        Intent intent= new Intent(this,SecondActivity.class);
        String message= mShowCount.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(intent,TEXT_REQUEST);
    }
}
