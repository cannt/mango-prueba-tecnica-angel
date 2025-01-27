package com.devskiller.notepadplus.ui.notes.list

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devskiller.notepadplus.data.repository.NoteRepository
import com.devskiller.notepadplus.domain.model.NoteEntity
import com.devskiller.notepadplus.ui.theme.NotepadTheme
import com.devskiller.notepadplus.ui.viewmodel.NoteViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun emptyState_showsWelcomeMessage() {
        composeTestRule.setContent {
            NotepadTheme { NoteListScreen(onAddNoteClick = {}, onNoteClick = {}, viewModel = fakeViewModel(emptyList())) }
        }
        composeTestRule.onNodeWithText("We do not have notes.").assertExists()
    }

    @Test
    fun notesDisplayedInGrid() {
        val notes = listOf(NoteEntity(title = "Note 1"), NoteEntity(title = "Note 2"))
        composeTestRule.setContent {
            NotepadTheme { NoteListScreen(onAddNoteClick = {}, onNoteClick = {}, viewModel = fakeViewModel(notes)) }
        }
        composeTestRule.onNodeWithText("Note 1").assertExists()
        composeTestRule.onNodeWithText("Note 2").assertExists()
    }

    private fun fakeViewModel(notes: List<NoteEntity>): NoteViewModel {
        val mockRepo = mockk<NoteRepository>()
        coEvery { mockRepo.allNotes } returns flowOf(notes)
        return NoteViewModel(mockRepo)
    }
}