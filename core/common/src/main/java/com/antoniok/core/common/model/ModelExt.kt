package com.antoniok.core.common.model

import androidx.compose.ui.graphics.Color
import com.antoniok.core.model.data.NoteColor

fun NoteColor.toColor(): Color = when (this) {
    NoteColor.Gray -> Color.Gray
    NoteColor.DarkGray -> Color.DarkGray
    NoteColor.LightGray -> Color.LightGray
    else -> throw Exception("Color not found.")
}
