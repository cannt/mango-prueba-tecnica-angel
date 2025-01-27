package com.devskiller.notepadplus.di

import android.content.Context
import androidx.room.Room
import com.devskiller.notepadplus.data.database.AppDatabase
import com.devskiller.notepadplus.data.repository.NoteRepository
import com.devskiller.notepadplus.ui.viewmodel.NoteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Database
    single {
        Room.databaseBuilder(
            get<Context>(),
            AppDatabase::class.java,
            "notes.db"
        ).build()
    }

    // DAO
    single { get<AppDatabase>().noteDao() }

    // Repository
    single { NoteRepository(get()) }

    // ViewModel
    viewModel { NoteViewModel(get()) }
}