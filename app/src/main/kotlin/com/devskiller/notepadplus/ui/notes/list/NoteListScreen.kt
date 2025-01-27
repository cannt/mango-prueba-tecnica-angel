package com.devskiller.notepadplus.ui.notes.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devskiller.notepadplus.R
import com.devskiller.notepadplus.domain.model.NoteEntity
import com.devskiller.notepadplus.ui.viewmodel.NoteViewModel

@Composable
fun NoteListScreen(
    onAddNoteClick: () -> Unit,
    onNoteClick: (NoteEntity) -> Unit,
    viewModel: NoteViewModel
) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())
    val selectedNotes = viewModel.selectedNotes

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = selectedNotes.isNotEmpty(),
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top)
            ) {
                NoteSelectedTopBar(
                    onClose = { viewModel.clearSelection() },
                    onDelete = { viewModel.deleteSelectedNotes() },
                    selectedNotes = selectedNotes.count()
                )
            }
        },
        floatingActionButton = {
            if (selectedNotes.isEmpty()) {
                FloatingActionButton(
                    onClick = onAddNoteClick,
                    content = { Icon(Icons.Default.Add, "Add note") }
                )
            }
        }
    ) { padding ->
        if (notes.isEmpty()) {
            WelcomeScreen(
                modifier = Modifier.padding(padding)
            )
        } else {
            Column(modifier = Modifier.padding(padding)) {
                val firstNote = notes.first()
                NoteItem(
                    modifier = Modifier
                        .padding(8.dp),
                    note = firstNote,
                    isSelected = selectedNotes.contains(firstNote.id),
                    onClick = {
                        if (selectedNotes.isNotEmpty()) {
                            viewModel.toggleNoteSelection(firstNote.id)
                        } else {
                            onNoteClick(firstNote)
                        }
                    },
                    onLongClick = { viewModel.toggleNoteSelection(firstNote.id) },
                )
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.weight(1f),
                    columns = StaggeredGridCells.Adaptive(minSize = 180.dp)
                ) {
                    items(notes.drop(1)) { note ->
                        NoteItem(
                            modifier = Modifier.padding(8.dp),
                            note = note,
                            isSelected = selectedNotes.contains(note.id),
                            onClick = {
                                if (selectedNotes.isNotEmpty()) {
                                    viewModel.toggleNoteSelection(note.id)
                                } else {
                                    onNoteClick(note)
                                }
                            },
                            onLongClick = { viewModel.toggleNoteSelection(note.id) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WelcomeScreen(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        text = stringResource(R.string.welcome_message),
        style = MaterialTheme.typography.headlineMedium
    )
}