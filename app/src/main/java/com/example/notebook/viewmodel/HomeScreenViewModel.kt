package com.example.notebook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook.model.NoteBookRepository
import com.example.notebook.model.entities.NoteData
import com.example.notebook.model.entities.NoteList
import com.example.notebook.viewmodel.states.UIStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(val repo: NoteBookRepository) : ViewModel() {

    private val _notes: MutableStateFlow<UIStatus> = MutableStateFlow(UIStatus.Empty)
    val notes get() = _notes.asStateFlow()

    private val _note: MutableStateFlow<NoteData> =
        MutableStateFlow(NoteData(title = "", body = "", completed = false))
    val note get() = _note.asStateFlow()

    fun addTodo(newNote: NoteData) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            repo.addNote(newNote)
        }
    }

    fun getNotes() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            val result = repo.getAllNotes()
            _notes.value = UIStatus.Notes(NoteList(result.first()))
        }
    }

    fun editNote(newNote: NoteData, pk: Int) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            newNote.let {
                repo.editNote(it.title, it.body, it.completed, pk)
            }
        }
    }

    fun removeNote(note: NoteData) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            repo.deleteNote(note = note)
        }
    }
//    fun getNote(index: Int): NoteData {
//        return try {
//            mockData.notes[index]
//        } catch (e: java.lang.IndexOutOfBoundsException) {
//            NoteData(title = "", body = "", completed = false)
//        }
//    }

//    fun search(title: String) {
//        val result = mockData.notes.filter { title == it.title }
//        _notes.value = UIStatus.Notes(notes = NoteList(notes = result.toMutableList()))
//    }

//    fun updateScreen() = viewModelScope.launch {
//        _notes.value = UIStatus.Notes(mockData)
//    }

//    fun delete(index: Int) {
//        try {
//            notes.value.let {
//                if (it is UIStatus.Notes && index < (it.notes.notes.size)) {
//                    it.notes.notes.removeAt(index = index)
//                }
//            }
//
//        } catch (e: IndexOutOfBoundsException) {
//            Log.d("HomeScreenViewModel", "delete: ${e.message}")
//        }
//    }
}