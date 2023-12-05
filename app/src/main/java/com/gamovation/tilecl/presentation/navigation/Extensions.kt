package com.gamovation.tilecl.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gamovation.core.navigation.Screens
import com.gamovation.core.navigation.toArg
import com.gamovation.core.ui.animation.Durations
import com.gamovation.feature.home.HomeScreen
import com.gamovation.feature.home.HomeScreenViewModel
import com.gamovation.feature.level.LevelScreen
import com.gamovation.feature.level.LevelScreenViewModel
import com.gamovation.feature.menu.presentation.MenuScreen
import com.gamovation.feature.menu.presentation.MenuViewModel
import com.gamovation.feature.settings.SettingsScreen
import com.gamovation.feature.settings.SettingsScreenViewModel
import com.gamovation.feature.store.presentation.StoreScreen
import com.gamovation.feature.store.presentation.StoreScreenViewModel

fun NavGraphBuilder.homeScreen(onNavigateToLevel: (Int) -> Unit) {
    composable(
        Screens.Home.route,
        enterTransition = {
            scaleIn(
                animationSpec = tween(Durations.Medium.time)
            )
        },
        exitTransition = {
            scaleOut(animationSpec = tween(Durations.Medium.time))
        }
    ) {
        val viewModel: HomeScreenViewModel = hiltViewModel()
        HomeScreen(
            viewModel = viewModel,
            onNavigateToLevel = onNavigateToLevel
        )
    }
}

fun NavGraphBuilder.storeScreen() {
    composable(
        Screens.Store.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(
                    Durations.Medium.time
                )
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(Durations.Medium.time)
            )
        }
    ) {
        val viewModel: StoreScreenViewModel = hiltViewModel()
        StoreScreen(viewModel = viewModel)
    }
}

fun NavGraphBuilder.settingsStore(onNavigateToHome: () -> Unit) {
    composable(Screens.Settings.route, enterTransition = {
        scaleIn(
            animationSpec = tween(Durations.Medium.time)
        )
    }, exitTransition = {
        scaleOut(animationSpec = tween(Durations.Medium.time))
    }) {
        val viewModel: SettingsScreenViewModel = hiltViewModel()
        SettingsScreen(
            viewModel = viewModel,
            onNavigateToHome = onNavigateToHome
        )
    }
}

fun NavGraphBuilder.menuScreen(onNavigateToLevel: (Int) -> Unit) {
    composable(Screens.Menu.route, enterTransition = {
        scaleIn(
            animationSpec = tween(Durations.Medium.time)
        )
    }, exitTransition = {
        scaleOut(animationSpec = tween(Durations.Medium.time))
    }) {
        val viewModel: MenuViewModel = hiltViewModel()
        MenuScreen(
            viewModel = viewModel,
            onNavigateToLevel = onNavigateToLevel
        )
    }
}

fun NavGraphBuilder.levelScreen(onNavigateToStore: () -> Unit) {
    val idArgName = Screens.Level().id.toArg()
    composable(
        Screens.Level().route,
        arguments = listOf(
            navArgument(
                idArgName
            ) {
                type = NavType.IntType
                defaultValue = 1
            }
        ),
        enterTransition = {
            fadeIn(tween(Durations.Medium.time))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(Durations.Medium.time))
        }
    ) { backStackEntry ->
        val viewModel: LevelScreenViewModel = hiltViewModel()
        val idArg = backStackEntry.arguments?.getInt(idArgName) ?: 1
        LevelScreen(
            viewModel = viewModel,
            idArg = idArg,
            onNavigateToStore = onNavigateToStore
        )
    }
}
