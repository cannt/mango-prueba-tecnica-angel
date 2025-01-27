package com.devskiller.notepadplus.data.repository

import com.devskiller.notepadplus.data.database.NoteDao
import com.devskiller.notepadplus.domain.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: Flow<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun insertNote(note: NoteEntity) = noteDao.insertNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

    suspend fun getNoteById(id: UUID): NoteEntity? = noteDao.getNoteById(id)
}