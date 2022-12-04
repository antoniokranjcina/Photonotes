package com.antoniok.core.designsystem.component.fab

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.antoniok.core.designsystem.icon.PnIcon

private const val LABEL_TRANSITION = "transition"
private const val LABEL_ALPHA = "alpha"
private const val LABEL_ROTATION = "rotation"

@Composable
fun MultiFloatingActionButton(
    fabIcon: PnIcon,
    items: List<MultiFabItem>,
    toState: MultiFabState,
    showLabels: Boolean = true,
    stateChanged: (fabState: MultiFabState) -> Unit,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {
    val transition: Transition<MultiFabState> = updateTransition(
        targetState = toState,
        label = LABEL_TRANSITION
    )
    val alpha: Float by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 50)
        },
        label = LABEL_ALPHA
    ) { state ->
        if (state == MultiFabState.EXPANDED) {
            1f
        } else {
            0f
        }
    }
    val rotation: Float by transition.animateFloat(label = LABEL_ROTATION) { state ->
        if (state == MultiFabState.EXPANDED) {
            45f
        } else {
            0f
        }
    }

    Column(horizontalAlignment = Alignment.End) {
        items.forEach { item ->
            MultiFabItem(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                item = item,
                alpha = alpha,
                isVisible = alpha == 1F,
                showLabel = showLabels,
                onFabItemClicked = onFabItemClicked
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        FloatingActionButton(
            onClick = {
                stateChanged(
                    if (transition.currentState == MultiFabState.EXPANDED) {
                        MultiFabState.COLLAPSED
                    } else {
                        MultiFabState.EXPANDED
                    }
                )
            }
        ) {
            when (fabIcon) {
                is PnIcon.ImageVectorIcon -> {
                    Icon(
                        modifier = Modifier.rotate(rotation),
                        imageVector = fabIcon.imageVector,
                        contentDescription = null
                    )
                }
                is PnIcon.DrawableResourceIcon -> {
                    Icon(
                        modifier = Modifier.rotate(rotation),
                        painter = painterResource(id = fabIcon.id),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
