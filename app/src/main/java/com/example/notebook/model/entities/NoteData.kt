package com.example.notebook.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val body: String,
    val completed: Boolean
)
