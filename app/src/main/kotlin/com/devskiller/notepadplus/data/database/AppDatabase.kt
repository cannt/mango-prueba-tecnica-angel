package com.devskiller.notepadplus.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devskiller.notepadplus.domain.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}