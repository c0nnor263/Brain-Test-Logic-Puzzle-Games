package com.conboi.wordefull.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.conboi.core.data.billing.store.StoreScreenDetails
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.level.ActionResult
import com.conboi.core.domain.level.LevelActionState
import com.conboi.core.domain.level.MAX_LEVEL_ID
import com.conboi.core.navigation.Screens
import com.conboi.core.navigation.toArg
import com.conboi.core.ui.Durations
import com.conboi.core.ui.extensions.cleanNavigate
import com.conboi.core.ui.level.rememberUserInteraction
import com.conboi.core.ui.state.LocalLevelActionState
import com.conboi.core.ui.state.LocalLevelScreenState
import com.conboi.feature.home.HomeScreen
import com.conboi.feature.home.HomeScreenViewModel
import com.conboi.feature.level.LevelScreen
import com.conboi.feature.level.LevelScreenViewModel
import com.conboi.feature.menu.MenuScreen
import com.conboi.feature.menu.MenuViewModel
import com.conboi.feature.settings.SettingsScreen
import com.conboi.feature.settings.SettingsScreenViewModel
import com.conboi.feature.store.StoreScreen
import com.conboi.feature.store.StoreScreenViewModel
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
                scaleIn(
                    animationSpec = tween(Durations.Medium.time)
                )
            },
            exitTransition = {
                scaleOut(animationSpec = tween(Durations.Medium.time))
            }
        ) {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            val levelData =
                viewModel.getLastUncompletedLevel()
                    .collectAsStateWithLifecycle(initialValue = null).value ?: LevelData()

            HomeScreen(
                onNavigateToLevel = {
                    navController.cleanNavigate(Screens.Level(levelData.id.toString()).route)
                }
            )
        }

        composable(
            Screens.Store.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(
                        Durations.Medium.time
                    )
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(
                        Durations.Medium.time
                    )
                )
            }
        ) {
            val activity = LocalContext.current as ComponentActivity
            val viewModel: StoreScreenViewModel = hiltViewModel()
            val productDetailsInfo = viewModel.getInAppProductsDetails()
                .collectAsStateWithLifecycle()

            val storeScreenDetails = remember(productDetailsInfo) {
                StoreScreenDetails.create(productDetailsInfo.value)
            }
            StoreScreen(
                storeDetails = storeScreenDetails,
                onWatchAd = { result -> if (result == true) viewModel.watchAdReward() },
                onBuy = { details, type ->
                    viewModel.purchaseProduct(details, type) { activity }
                }
            )
        }

        composable(
            Screens.Settings.route,
            enterTransition = {
                scaleIn(
                    animationSpec = tween(Durations.Medium.time)
                )
            },
            exitTransition = {
                scaleOut(animationSpec = tween(Durations.Medium.time))
            }
        ) {
            val viewModel: SettingsScreenViewModel = hiltViewModel()
            SettingsScreen {
                viewModel.restorePurchases()
            }
        }

        composable(
            Screens.Menu.route,
            enterTransition = {
                scaleIn(
                    animationSpec = tween(Durations.Medium.time)
                )
            },
            exitTransition = {
                scaleOut(animationSpec = tween(Durations.Medium.time))
            }
        ) {
            val viewModel: MenuViewModel = hiltViewModel()
            val levelDataList =
                viewModel.getAllLevels().collectAsStateWithLifecycle(emptyList())


            var pageIndex by remember {
                mutableStateOf(0)
            }

            val list =
                levelDataList.value.takeIf { it.isNotEmpty() }?.subList(
                    pageIndex, (pageIndex + 5).coerceAtMost(
                        MAX_LEVEL_ID
                    )
                )
                    ?: listOf()
            MenuScreen(
                index = pageIndex,
                levelList = list,
                onNavigateToLevel = { levelId: Int ->
                    navController.cleanNavigate(Screens.Level(levelId.toString()).route)
                },
                onIndexUpdate = {
                    pageIndex = it.coerceAtMost(MAX_LEVEL_ID)
                }
            )
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


            val levelData = viewModel.levelData.collectAsStateWithLifecycle()
            val levelScreenState = viewModel.levelScreenState.collectAsStateWithLifecycle()
            val levelActionState = viewModel.levelActionState.collectAsStateWithLifecycle()


            val userInteractionState = rememberUserInteraction()
            val interaction = userInteractionState.interactionFlow.collectAsStateWithLifecycle()


            LaunchedEffect(idArg) {
                viewModel.updateLevelIndex(idArg)
            }

            LaunchedEffect(interaction.value) {
                viewModel.processInteraction(interaction.value)
            }

            CompositionLocalProvider(
                LocalLevelScreenState provides levelScreenState.value,
                LocalLevelActionState provides levelActionState.value
            ) {
                LevelScreen(
                    level = levelData.value,
                    userInteractionState = userInteractionState,
                    onActionResult = { actionResult ->
                        when (actionResult.type) {
                            ActionResult.Type.SUCCESS -> {
                                viewModel.buyAction(cost = actionResult.cost)
                            }

                            ActionResult.Type.BUY_MORE -> {
                                navController.cleanNavigate(Screens.Store.route)
                            }

                            else -> {}
                        }
                        viewModel.updateLevelActionState(LevelActionState.IDLE)
                    },

                    )
            }


        }


    }
}