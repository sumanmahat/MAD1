package com.example.todoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes= new ArrayList<>();


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent,false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {

        Note currentnote= notes.get(i);
        noteHolder.title.setText(currentnote.getTitle());
        noteHolder.description.setText(currentnote.getDescription());
        noteHolder.priority.setText(String.valueOf(currentnote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes= notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView priority;
        private TextView description;

        public NoteHolder(View itemView){
            super(itemView);
            title= itemView.findViewById(R.id.title);
            priority= itemView.findViewById(R.id.text_view_priority);
            description= itemView.findViewById(R.id.description);


        }

    }
}
