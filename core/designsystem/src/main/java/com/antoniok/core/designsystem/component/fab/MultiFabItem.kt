package com.antoniok.core.designsystem.component.fab

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antoniok.core.designsystem.icon.PnIcon
import com.antoniok.core.designsystem.icon.PnIcons


enum class MultiFabItem(
    val icon: PnIcon,
    val label: String
) {
    PHOTO(
        icon = PnIcon.DrawableResourceIcon(PnIcons.TakePhoto),
        label = "Photo Note"
    ),
    TEXT(
        icon = PnIcon.DrawableResourceIcon(PnIcons.Pen),
        label = "Text Note"
    )
}

val multiFabItems = listOf(MultiFabItem.PHOTO, MultiFabItem.TEXT)

@Composable
internal fun MultiFabItem(
    modifier: Modifier = Modifier,
    item: MultiFabItem,
    alpha: Float,
    isVisible: Boolean,
    showLabel: Boolean,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showLabel) {
            Text(
                text = item.label,
                fontSize = 14.sp,
                modifier = Modifier
                    .alpha(animateFloatAsState(alpha).value)
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }

        if (isVisible) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable(
                        onClick = { onFabItemClicked(item) },
                        indication = rememberRipple(bounded = false),
                        interactionSource = interactionSource
                    )
                    .background(
                        color = MaterialTheme.colors.secondary,
                        shape = CircleShape
                    )
                    .alpha(animateFloatAsState(alpha).value),
                contentAlignment = Alignment.Center
            ) {
                when (item.icon) {
                    is PnIcon.ImageVectorIcon -> {
                        Icon(
                            imageVector = item.icon.imageVector,
                            contentDescription = null,
                        )
                    }
                    is PnIcon.DrawableResourceIcon -> {
                        Icon(
                            painter = painterResource(id = item.icon.id),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MultiFabItemPreview() {
    MultiFabItem(
        item = MultiFabItem.TEXT,
        alpha = 1f,
        isVisible = true,
        showLabel = true,
        onFabItemClicked = {},
    )
}
