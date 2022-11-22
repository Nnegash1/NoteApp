package com.example.notebook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notebook.model.NoteBookRepository
import javax.inject.Inject

class HomeNoteFactory @Inject constructor(
    private val repo: NoteBookRepository,
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(
                repo = repo,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}