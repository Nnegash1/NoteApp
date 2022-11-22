package com.example.notebook.viewmodel.states

import com.example.notebook.model.entities.NoteList

sealed class UIStatus() {
    class Notes(val notes: NoteList) : UIStatus()
    object Empty : UIStatus()
}
