package com.antoniok.core.model.data

data class PhotoNote(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val color: NoteColor
)

val previewPhotoNotes = listOf(
    PhotoNote(
        id = "1",
        title = "Budget expenses",
        description = "I have bought this tea today. Because we actually need tee in order to stop",
        date = "11.2.2022.",
        color = NoteColor.Gray
    ),
    PhotoNote(
        id = "2",
        title = "Car fixes",
        description = "We need to buy oil for the car.",
        date = "13.10.2022.",
        color = NoteColor.DarkGray
    ),
    PhotoNote(
        id = "3",
        title = "Lecture notes",
        description = "Today we studied about electrical current.",
        date = "15.11.2021.",
        color = NoteColor.LightGray
    )
)
