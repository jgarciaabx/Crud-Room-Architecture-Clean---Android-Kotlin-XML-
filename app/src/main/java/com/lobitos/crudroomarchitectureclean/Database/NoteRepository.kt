package com.lobitos.crudroomarchitectureclean.Database

import androidx.lifecycle.LiveData
import com.lobitos.crudroomarchitectureclean.Models.Note

class NoteRepository(private val noteDao: NoteDao){

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note.id!!, note.title, note.note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }


}