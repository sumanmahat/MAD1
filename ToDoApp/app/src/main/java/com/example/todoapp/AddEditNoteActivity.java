package com.example.todoapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.todoapp.database.Note;

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
    public static final String EXTRA_DATETIME=
            "com.example.todoapp.EXTRA_DATETIME";

    private EditText editTitle, editDescription,newTodoDateAndTime;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        numberPicker = findViewById(R.id.number_picker);
     //   dateAndTime= findViewById(R.id.newTodoDateAndTime);
        newTodoDateAndTime = findViewById(R.id.newTodoDateAndTime);

        newTodoDateAndTime.setOnClickListener(this);


        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(3);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
            newTodoDateAndTime.setText(intent.getStringExtra(EXTRA_DATETIME));

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

        int id= getIntent().getIntExtra(EXTRA_ID,-1);

        NoteViewModel noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        Note note= new Note(title,description,priority,dateAndTime);
        note.setId(id);
        noteViewModel.update(note);
        Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
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
        new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            final String date = year1 + "/" + month1 + "/" + dayOfMonth;
            new TimePickerDialog(AddEditNoteActivity.this, (view1, hourOfDay, minute1) -> {
                String time = hourOfDay + ":" + minute1;
                newTodoDateAndTime.setText(date + " " + time);
            }, hour, minute, false).show();
        }, year, month, day).show();
    }
}