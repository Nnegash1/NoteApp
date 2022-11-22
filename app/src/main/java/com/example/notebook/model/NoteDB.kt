package com.example.notebook.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notebook.model.entities.NoteData

@Database(entities = [NoteData::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase() {
    abstract fun getDao(): DAO

    companion object {
        const val NOTEBOOK_TABLE = "notebook.db"
    }

}