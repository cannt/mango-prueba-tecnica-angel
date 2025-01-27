package com.devskiller.notepadplus

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigateToEditScreen_onNoteClick() {
        composeTestRule.setContent { NotepadApp() }
        composeTestRule.onNodeWithContentDescription("Add note").performClick()
        composeTestRule.onNodeWithTag("titleTextField").performTextInput("Test Title")
        composeTestRule.onNodeWithTag("descriptionTextField").performTextInput("Test Description")
        composeTestRule.onNodeWithContentDescription("Save").assertExists()
    }
}