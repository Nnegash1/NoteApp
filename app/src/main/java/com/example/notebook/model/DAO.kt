package com.example.notebook.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notebook.model.entities.NoteData
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {

    @Insert
    suspend fun insertNote(note: NoteData)

    @Query("Select * from NoteData")
    fun getAllNotes(): Flow<List<NoteData>>

    @Query("Update NoteData set title=:title, body=:body, completed=:complete where id=:pk")
    suspend fun update(title: String, body: String, complete: Boolean, pk: Int)

    @Query("Select * from NoteData where id=:pk limit 1")
    fun getNote(pk: Int): Flow<NoteData>

    @Delete
    fun removeNote(note: NoteData)
}