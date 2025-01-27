package com.devskiller.notepadplus.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devskiller.notepadplus.domain.model.NoteEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NoteDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.noteDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveNote() = runTest {
        val note = NoteEntity(title = "Test Title")
        dao.upsertNote(note)
        val retrieved = dao.getNoteById(note.id)
        assertEquals(note.title, retrieved?.title)
    }

    @Test
    fun deleteNote_removesFromDatabase() = runTest {
        val note = NoteEntity(title = "Test Title")
        dao.upsertNote(note)
        dao.deleteNote(note)
        val result = dao.getNoteById(note.id)
        assertNull(result)
    }
}