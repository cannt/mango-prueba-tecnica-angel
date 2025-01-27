package com.devskiller.notepadplus.ui.viewmodel

import com.devskiller.notepadplus.data.repository.NoteRepository
import com.devskiller.notepadplus.domain.model.NoteEntity
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.UUID

class NoteViewModelTest {
    private val mockRepo = mockk<NoteRepository>()
    private lateinit var viewModel: NoteViewModel

    @Before
    fun setup() {
        viewModel = NoteViewModel(mockRepo)
    }

    @Test
    fun toggleNoteSelection_updatesSelectedList() {
        val uuid = UUID.randomUUID()
        viewModel.toggleNoteSelection(uuid)
        assertTrue(uuid in viewModel.selectedNotes)
        viewModel.toggleNoteSelection(uuid)
        assertFalse(uuid in viewModel.selectedNotes)
    }

    @Test
    fun deleteSelectedNotes_callsRepoForEach() = runTest {
        val note1 = NoteEntity()
        val note2 = NoteEntity()
        coEvery { mockRepo.getNoteById(any()) } returnsMany listOf(note1, note2)
        coEvery { mockRepo.deleteNote(any()) } just Runs

        viewModel.toggleNoteSelection(note1.id)
        viewModel.toggleNoteSelection(note2.id)
        viewModel.deleteSelectedNotes()

        coVerify(exactly = 2) { mockRepo.deleteNote(any()) }
    }
}