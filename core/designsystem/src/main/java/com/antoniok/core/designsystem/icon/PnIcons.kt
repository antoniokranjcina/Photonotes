package com.antoniok.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import com.antoniok.core.designsystem.R

object PnIcons {
    val Add = Icons.Default.Add
    val ArrowBack = Icons.Default.ArrowBack

    val Pen = R.drawable.ic_pen

    val TakePhoto = R.drawable.ic_take_photo
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class PnIcon {
    data class ImageVectorIcon(val imageVector: ImageVector) : PnIcon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : PnIcon()
}
