package com.example.todoapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddEditNoteActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_ID=
            "com.example.todoapp.EXTRA_ID";
    public static final String EXTRA_TITLE=
            "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION=
            "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY=
            "com.example.todoapp.EXTRA_PRIORITY";

    private EditText editTitle, editDescription,newTodoDateAndTime;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        numberPicker = findViewById(R.id.number_picker);
        newTodoDateAndTime = findViewById(R.id.newTodoDateAndTime);

        newTodoDateAndTime.setOnClickListener(this);


        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));

        }else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    private void saveNote(){
        String title= editTitle.getText().toString();
        String description= editDescription.getText().toString();
        int priority =  numberPicker.getValue();
        String dateAndTime = newTodoDateAndTime.getText().toString();
        if (title.trim().isEmpty() || description.trim().isEmpty() || dateAndTime.trim().isEmpty()){
            Toast.makeText(this, "Please insert title, description, date and time.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent= new Intent();
        intent.putExtra(EXTRA_TITLE,title);
        intent.putExtra(EXTRA_DESCRIPTION,description);
        intent.putExtra(EXTRA_PRIORITY,priority);

        int id= getIntent().getIntExtra(EXTRA_ID,-1);
        if (id !=-1){
            intent.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,intent);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        final int hour = calender.get(Calendar.HOUR_OF_DAY);
        final int minute = calender.get(Calendar.MINUTE);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final String date = year + "/" + month + "/" + dayOfMonth;
                new TimePickerDialog(AddEditNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        newTodoDateAndTime.setText(date + " " + time);
                    }
                }, hour, minute, false).show();
            }
        }, year, month, day).show();
    }
}