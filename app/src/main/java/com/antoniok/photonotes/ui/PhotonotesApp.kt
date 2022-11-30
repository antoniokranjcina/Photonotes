package com.antoniok.photonotes.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.zIndex
import com.antoniok.core.designsystem.component.PnTopAppBar
import com.antoniok.core.designsystem.icon.PnIcons
import com.antoniok.core.designsystem.theme.PnTheme
import com.antoniok.core.ui.VnFloatingActionButton
import com.antoniok.photonotes.navigation.PnNavHost

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun PhotonotesApp(
    appState: PhotonotesAppState = rememberPhotonotesAppState()
) {
    PnTheme {
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colors.background,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                // Show the same top bar on top level destinations
                val destination = appState.currentTopLevelDestination
                if (destination != null) {
                    PnTopAppBar(
                        // When the nav rail is displayed, the top app bar will, by default
                        // overlap it. This means that the top most item in the nav rail
                        // won't be tappable. A workaround is to position the top app bar
                        // behind the nav rail using zIndex.
                        modifier = Modifier.zIndex(-1F),
                        titleRes = destination.titleTextId,
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )
                }
            },
            floatingActionButton = {
                if (appState.currentTopLevelDestination != null) {
                    VnFloatingActionButton(
                        onShortClick = { appState.setShowNewNoteDialog(true) },
                        onLongClick = {
                            // TODO show two choices:
                            //  1.) voice note
                            //  2.) text note
                        }
                    ) {
                        Icon(imageVector = PnIcons.Add, contentDescription = null)
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
        ) { padding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                    )
            ) {
                if (appState.shouldShowNewNoteDialog) {
                    // Show save dialog
                }

                PnNavHost(
                    navController = appState.navController,
                    onBackClick = appState::onBackClick,
                    modifier = Modifier
                        .padding(padding)
                        .consumedWindowInsets(padding)
                )
            }
        }
    }
}
