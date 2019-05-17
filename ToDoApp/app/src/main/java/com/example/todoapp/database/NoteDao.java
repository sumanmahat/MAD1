package com.example.todoapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.todoapp.database.Note;

import java.util.List;



@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("Delete FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * from note_table order by priority desc")
    LiveData<List<Note>> getAllNotes();


}