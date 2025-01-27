package com.devskiller.notepadplus.ui.notes.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.devskiller.notepadplus.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteSelectedTopBar(
    selectedNotes: Int,
    onClose: () -> Unit,
    onDelete: () -> Unit
) {
    TopAppBar(
        title = { Text(
            if(selectedNotes>1) stringResource(R.string.selected_count_plural, selectedNotes)
            else stringResource(R.string.selected_count_singular)
        ) },
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, "Close")
            }
        },
        actions = {
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, "Delete")
            }
        }
    )
}
