package com.example.thelayouteditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_toast, btn_zero, btn_count;
    TextView txt_count;
    public Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_toast= (Button) findViewById(R.id.btn_toast);
        btn_count= findViewById(R.id.btn_count);
        btn_zero= findViewById(R.id.btn_zero);
        txt_count= findViewById(R.id.txt_count);
        
        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This is a tost", Toast.LENGTH_SHORT).show();
            }
        });

        btn_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increasecount();
            }
        });


    }
    public void reset(){
        count=0;
        txt_count.setText(count.toString());
        btn_zero.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

    }

    public void increasecount(){
        count++;

        if (count != null){
            txt_count.setText(count.toString());

            if (count %2 ==0){
                btn_zero.setBackgroundColor(getResources().getColor(R.color.Foreven));
            }else {
                btn_zero.setBackgroundColor(getResources().getColor(R.color.Forodd));
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
