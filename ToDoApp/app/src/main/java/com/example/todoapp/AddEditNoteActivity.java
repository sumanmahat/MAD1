package com.example.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID=
            "com.example.todoapp.EXTRA_ID";
    public static final String EXTRA_TITLE=
            "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION=
            "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY=
            "com.example.todoapp.EXTRA_PRIORITY";

    private EditText editTitle, editDescription;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        numberPicker = findViewById(R.id.number_picker);


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
        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please insert title and description", Toast.LENGTH_SHORT).show();
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
}