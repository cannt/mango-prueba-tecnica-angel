package com.devskiller.notepadplus.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devskiller.notepadplus.data.repository.NoteRepository
import com.devskiller.notepadplus.domain.model.NoteEntity
import kotlinx.coroutines.launch
import java.util.UUID

class NoteViewModel(
    val repository: NoteRepository
) : ViewModel() {
    val notes = repository.allNotes
    private var _selectedNotes = mutableStateListOf<UUID>()
    val selectedNotes: List<UUID> = _selectedNotes


    fun toggleNoteSelection(noteId: UUID) {
        if (noteId in selectedNotes) {
            _selectedNotes.remove(noteId)
        } else {
            _selectedNotes.add(noteId)
        }
    }

    fun deleteSelectedNotes() {
        viewModelScope.launch {
            selectedNotes.forEach { id ->
                repository.getNoteById(id)?.let { note ->
                    repository.deleteNote(note)
                }
            }
            _selectedNotes.clear()
        }
    }

    fun clearSelection() {
        _selectedNotes.clear()
    }

    fun upsertNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.upsertNote(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}