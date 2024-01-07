package com.example.kotlinnotesapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinnotesapp.data.Note
import com.example.kotlinnotesapp.data.NoteDao
import com.example.kotlinnotesapp.data.NoteDatabase

class NoteRepository(val context: Context) {
    private var dao: NoteDao = NoteDatabase(context).dao()

    public fun insert(note: Note) = dao.insert(note)
    public fun update(note: Note) = dao.update(note)
    public fun delete(note: Note) = dao.delete(note)

    public fun getNotes(): LiveData<List<Note>> = dao.getNotes()
}
