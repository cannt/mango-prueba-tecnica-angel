package com.devskiller.notepadplus.data.repository

import com.devskiller.notepadplus.data.database.NoteDao
import com.devskiller.notepadplus.domain.model.NoteEntity
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.UUID

class NoteRepositoryTest {
    private lateinit var mockDao: NoteDao
    private lateinit var repository: NoteRepository

    @Before
    fun setup() {
        mockDao = mockk()

        coEvery { mockDao.getAllNotes() } returns flowOf(emptyList())
        repository = NoteRepository(mockDao)
    }

    @Test
    fun upsertNote_callsDaoUpsert() = runTest {
        val note = NoteEntity(title = "title")
        coEvery { mockDao.upsertNote(note) } just Runs

        repository.upsertNote(note)

        coVerify { mockDao.upsertNote(note) }
    }

    @Test
    fun getNoteById_callsDao() = runTest {
        val uuid = UUID.randomUUID()
        coEvery { mockDao.getNoteById(uuid) } returns NoteEntity(id = uuid, title = "title")

        repository.getNoteById(uuid)

        coVerify { mockDao.getNoteById(uuid) }
    }
}