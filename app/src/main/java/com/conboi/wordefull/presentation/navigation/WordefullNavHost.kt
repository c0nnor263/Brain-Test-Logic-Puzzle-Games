package com.conboi.wordefull.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.conboi.core.navigation.Screens
import com.conboi.core.navigation.toArg
import com.conboi.core.ui.Durations
import com.conboi.feature.home.HomeScreen
import com.conboi.feature.home.HomeScreenViewModel
import com.conboi.feature.level.LevelScreen
import com.conboi.feature.level.LevelScreenViewModel
import com.conboi.feature.menu.MenuScreen
import com.conboi.feature.settings.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WordefullNavHost(navController: NavHostController) {
    AnimatedNavHost(
        modifier = Modifier
            .fillMaxWidth()
            .clipToBounds(),
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(
            Screens.Home.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(Durations.Medium.time)
                )
            },
            exitTransition = {

                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(Durations.Medium.time)
                )
            },
        ) {
            val viewModel: HomeScreenViewModel = hiltViewModel()

            HomeScreen(viewModel = viewModel, onNavigateToLevel = { levelId: Int ->
                navController.navigate(Screens.Level(levelId.toString()).route)
            })
        }

        composable(
            Screens.Settings.route,

            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(Durations.Medium.time)
                )
            },
            exitTransition = {

                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(Durations.Medium.time)
                )
            }
        ) {
            SettingsScreen()
        }

        composable(
            Screens.Menu.route,


            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(Durations.Medium.time)
                )
            },
            exitTransition = {

                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(Durations.Medium.time)
                )
            }) {
            MenuScreen(onNavigateToLevel = { levelId: Int ->
                navController.navigate(Screens.Level(levelId.toString()).route)
            })
        }


        val idArgName = Screens.Level().id.toArg()
        composable(
            Screens.Level().route, arguments = listOf(
                navArgument(
                    idArgName,
                ) {
                    type = NavType.IntType
                    defaultValue = 1
                },
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
            LevelScreen(entryLevelId = idArg, viewModel = viewModel)
        }
    }
}