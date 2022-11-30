package com.antoniok.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Line(modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .width(1.dp)
            .height(16.dp)
            .background(Color.Black)
    )
}
