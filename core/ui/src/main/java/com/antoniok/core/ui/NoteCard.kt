package com.antoniok.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.antoniok.core.designsystem.component.Spacer8
import com.antoniok.core.designsystem.theme.Padding

private const val TITLE_MAX_LINES = 2
private const val DESCRIPTION_MAX_LINES = 1

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    date: String,
    color: Color
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .background(color)
            .padding(Padding.Medium)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = TITLE_MAX_LINES,
            overflow = TextOverflow.Ellipsis
        )
        Spacer8()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer8()
            Line()
            Spacer8()
            Text(
                text = description,
                style = MaterialTheme.typography.labelMedium,
                maxLines = DESCRIPTION_MAX_LINES,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun NoteCardPreview() {
    NoteCard(
        title = "Troškovi",
        description = "Gorivo za putne troškove",
        date = "14.3.2022.",
        color = Color.DarkGray
    )
}
