package com.example.todoapp.fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.AddEditNoteActivity;
import com.example.todoapp.database.Note;
import com.example.todoapp.NoteAdapter;
import com.example.todoapp.NoteViewModel;
import com.example.todoapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public static final int ADD_NOTE_REQUEST= 1;
    public static final int EDIT_NOTE_REQUEST= 2;
    private RecyclerView recyclerView;
    private EditText etSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        etSearch = v.findViewById(R.id.etSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter= new NoteAdapter();
        recyclerView.setAdapter(adapter);


        NoteViewModel noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, notes -> adapter.setNotes(notes));

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<Note> filtered = new ArrayList<>();
                String text = editable.toString().toLowerCase();

                for (Note note : noteViewModel.getAllNotes().getValue()){
                    if(note.getTitle().toLowerCase().contains(text)){
                        filtered.add(note);
                    }
                }
                adapter.setNotes(filtered);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("You want to delete this task ?");
                dialog.setPositiveButton("Yes", (dialogInterface, i1) -> {
                    noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getActivity(), "Note Deleted", Toast.LENGTH_SHORT).show();
                });
                dialog.setNegativeButton("No", (dialogInterface, i1) -> {dialogInterface.dismiss();
                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());});
                dialog.show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(note -> {
            Intent intent= new Intent(getActivity(), AddEditNoteActivity.class);
            intent.putExtra(AddEditNoteActivity.EXTRA_ID,note.getId());
            intent.putExtra(AddEditNoteActivity.EXTRA_TITLE,note.getTitle());
            intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION,note.getDescription());
            intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY,note.getPriority());
            startActivityForResult(intent,EDIT_NOTE_REQUEST);
        });
        return v;
    }
}