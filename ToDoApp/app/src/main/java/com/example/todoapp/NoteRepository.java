package com.example.todoapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;



public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database= NoteDatabase.getInstance(application);
        noteDao= database.noteDao();
        allNotes= noteDao.getAllNotes();
    }


        public void insert(Note note){
            new InsertNotesAsyncTask(noteDao).execute(note);

        }

        public void update(Note note){
            new UpdateNotesAsyncTask(noteDao).execute(note);
        }
        public void delete(Note note){
            new DeleteNotesAsyncTask(noteDao).execute(note);

        }

        public void deleteAllNotes(){
            new DeleteAllNotesAsyncTask(noteDao).execute();

        }

        public LiveData<List<Note>> getAllNotes(){
                return allNotes;
        }

        private static class InsertNotesAsyncTask extends AsyncTask<Note, Void,Void>{
            private NoteDao noteDao;

            private InsertNotesAsyncTask(NoteDao noteDao){
                this.noteDao= noteDao;
            }
            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.insert(notes[0]);
                return null;
            }
        }

    private static class UpdateNotesAsyncTask extends AsyncTask<Note, Void,Void>{
        private NoteDao noteDao;

        private UpdateNotesAsyncTask(NoteDao noteDao){
            this.noteDao= noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNotesAsyncTask extends AsyncTask<Note, Void,Void>{
        private NoteDao noteDao;

        private DeleteNotesAsyncTask(NoteDao noteDao){
            this.noteDao= noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void,Void>{
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao= noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

    }

