package com.devskiller.notepadplus.domain.model

import android.content.res.Resources
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devskiller.notepadplus.R
import java.util.UUID

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String =  Resources.getSystem().getString(R.string.new_note),
    var description: String? = null,
    val created: Long = System.currentTimeMillis()
)