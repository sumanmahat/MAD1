package com.example.todoapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.todoapp.database.Note;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.todoapp.EXTRA_PRIORITY";

    private EditText editTitle, editDescription, newTodoDateAndTime;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        numberPicker = findViewById(R.id.number_picker);
        newTodoDateAndTime = findViewById(R.id.newTodoDateAndTime);


        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    private void saveNote() {
        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();
        String dateTime= newTodoDateAndTime.getText().toString();
        int priority = numberPicker.getValue();
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        NoteViewModel noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        Note note = new Note(title, description, priority,dateTime);
        noteViewModel.insert(note);
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

    public void openDatePicker(View view) {
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        final int hour = calender.get(Calendar.HOUR_OF_DAY);
        final int minute = calender.get(Calendar.MINUTE);
        new DatePickerDialog(this, (v, year1, month1, dayOfMonth) -> {
            final String date = year1 + "/" + month1 + "/" + dayOfMonth;
            new TimePickerDialog(AddNoteActivity.this, (view1, hourOfDay, minute1) -> {
                String time = hourOfDay + ":" + minute1;
                newTodoDateAndTime.setText(date + " " + time);
            }, hour, minute, false).show();
        }, year, month, day).show();
    }
}
