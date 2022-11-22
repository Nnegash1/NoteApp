package com.example.notebook.model

import com.example.notebook.model.entities.NoteData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteBookRepository @Inject constructor(private val dao: DAO) {

    fun getAllNotes(): Flow<List<NoteData>> = dao.getAllNotes()

    suspend fun addNote(note: NoteData) = dao.insertNote(note)

    suspend fun editNote(title: String, body: String, completed: Boolean, pk: Int) {
        return dao.update(title, body, completed, pk)
    }

    fun getNote(pk: Int): Flow<NoteData> = dao.getNote(pk)

    fun deleteNote(note: NoteData) = dao.removeNote(note)
}