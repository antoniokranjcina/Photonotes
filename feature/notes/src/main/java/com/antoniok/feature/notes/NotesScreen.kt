package com.antoniok.feature.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.common.model.toColor
import com.antoniok.core.designsystem.theme.Padding
import com.antoniok.core.model.data.PhotoNote
import com.antoniok.core.model.data.previewPhotoNotes
import com.antoniok.core.ui.NoteCard

@Composable
fun NotesRoute(modifier: Modifier = Modifier) {
    NotesScreen(
        modifier = modifier,
        notes = emptyList()
    )
}

@Composable
internal fun NotesScreen(
    modifier: Modifier = Modifier,
    notes: List<PhotoNote>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(Padding.Medium),
        verticalArrangement = Arrangement.spacedBy(Padding.Medium),
    ) {
        items(notes) { note ->
            NoteCard(
                title = note.title,
                description = note.description,
                date = note.date,
                color = note.color.toColor()
            )
        }
    }
}

@Preview
@Composable
private fun NotesScreenPreview() {
    NotesScreen(notes = previewPhotoNotes)
}
