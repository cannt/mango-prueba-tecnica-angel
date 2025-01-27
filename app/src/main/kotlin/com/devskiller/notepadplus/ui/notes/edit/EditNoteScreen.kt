package com.devskiller.notepadplus.ui.notes.edit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devskiller.notepadplus.R
import com.devskiller.notepadplus.domain.model.NoteEntity
import com.devskiller.notepadplus.ui.viewmodel.NoteViewModel
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun EditNoteScreen(
    noteId: String?,
    onBack: () -> Unit,
    viewModel: NoteViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var processing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (noteId != null && noteId != "new") {
            try {
                val uuid = UUID.fromString(noteId)
                viewModel.repository.getNoteById(uuid)?.let { note ->
                    title = note.title
                    description = note.description ?: ""
                }
            } catch (e: IllegalArgumentException) {
                onBack()
            }
        }
    }

    LaunchedEffect(noteId) {
        if (noteId != null && noteId != "new") {
            try {
                val uuid = UUID.fromString(noteId)
                coroutineScope.launch {
                    viewModel.repository.getNoteById(uuid)?.let { note ->
                        title = note.title
                        description = note.description ?: ""
                    }
                }
            } catch (e: IllegalArgumentException) {
                onBack()
            }
        }
    }

    Scaffold(
        topBar = {
            EditNoteTopBar(
                noteId = noteId ?: "new",
                onBack = onBack,
                onDelete = {
                    coroutineScope.launch{
                        val uuid = UUID.fromString(noteId)
                        viewModel.repository.getNoteById(uuid)?.let { note ->
                            onBack()
                            viewModel.deleteNote(note)
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = title.isNotBlank() || description.isNotBlank(),
                enter = scaleIn(),
                exit = scaleOut()
            ){
                FloatingActionButton(
                    modifier = Modifier.imePadding(),
                    onClick = {
                        if(!processing){
                            if (title.isBlank()) {
                                showError = true
                            } else {
                                processing = true
                                val newNote = try {
                                    NoteEntity(
                                        id = if (noteId == "new") UUID.randomUUID()
                                        else UUID.fromString(noteId),
                                        title = title,
                                        description = description.ifBlank { null }
                                    )
                                } catch (e: Exception) {
                                    NoteEntity(
                                        title = title,
                                        description = description.ifBlank { null }
                                    )
                                }
                                viewModel.upsertNote(newNote)
                                onBack()
                            }
                        }
                    }
                ) {
                    Icon(painterResource(R.drawable.ic_save), "Save")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {
                BasicTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .testTag("titleTextField")
                        .fillMaxWidth()
                        .padding(0.dp),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    textStyle = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    decorationBox = { innerTextField ->
                        if (title.isEmpty()) {
                            Text(
                                text = stringResource(R.string.title),
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            )
                        }
                        innerTextField()
                    }
                )

                if (showError) {
                    Text(
                        text = stringResource(R.string.field_not_be_empty_error),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .testTag("descriptionTextField")
                    .fillMaxSize()
                    .weight(1f)
                    .padding(0.dp),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                decorationBox = { innerTextField ->
                    innerTextField()
                }
            )
        }
    }
}