package com.antoniok.photonotes.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.antoniok.feature.notes.navigation.navigateToNotes
import com.antoniok.feature.notes.navigation.notesNavigationRoute
import com.antoniok.photonotes.navigation.TopLevelDestination


@Composable
fun rememberPhotonotesAppState(
    navController: NavHostController = rememberNavController()
): PhotonotesAppState {
    return remember(navController) {
        PhotonotesAppState(navController)
    }
}

@Stable
class PhotonotesAppState(
    val navController: NavHostController
) {


    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            notesNavigationRoute -> TopLevelDestination.NOTES
            else -> null
        }

    var shouldShowNewNoteDialog by mutableStateOf(false)
        private set

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.NOTES -> navController.navigateToNotes(topLevelNavOptions)
            }
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }

    fun setShowNewNoteDialog(shouldShow: Boolean) {
        shouldShowNewNoteDialog = shouldShow
    }

}
