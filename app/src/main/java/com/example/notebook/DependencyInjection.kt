package com.example.notebook

import android.app.Application
import androidx.room.Room
import com.example.notebook.model.NoteBookRepository
import com.example.notebook.model.NoteDB
import com.example.notebook.viewmodel.HomeScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DependencyInjection {

    @Singleton
    @Provides
    fun instanceDataBase(app: Application): NoteDB {
        return Room.databaseBuilder(
            app,
            NoteDB::class.java,
            NoteDB.NOTEBOOK_TABLE
        ).build()
    }

    @Provides
    fun provideViewModel(repo: NoteBookRepository): HomeScreenViewModel {
        return HomeScreenViewModel(repo)
    }

    @Provides
    @Singleton
    fun provideNoteRepo(dao: NoteDB): NoteBookRepository {
        return NoteBookRepository(dao.getDao())
    }
}