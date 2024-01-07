package com.example.kotlinnotesapp.viewmodel

import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinnotesapp.data.Note
import com.example.kotlinnotesapp.repository.NoteRepository

class NoteViewModel(val context: Context): ViewModel() {
    private val repository = NoteRepository(context)
    public var notes: LiveData<List<Note>> = repository.getNotes()

    fun insert(note: Note){
        repository.insert(note)
    }

    fun update(note: Note){
        repository.update(note)
    }

    fun delete(note: Note){
        repository.delete(note)
    }
}