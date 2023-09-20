package com.gamovation.tilecl.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedContentTransitionScope
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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gamovation.core.data.billing.store.StoreScreenDetails
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.domain.level.ActionResult
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.domain.level.MAX_LEVEL_ID
import com.gamovation.core.navigation.Screens
import com.gamovation.core.navigation.toArg
import com.gamovation.core.ui.Durations
import com.gamovation.core.ui.extensions.navigate
import com.gamovation.core.ui.level.rememberUserInteraction
import com.gamovation.core.ui.state.LocalLevelActionState
import com.gamovation.core.ui.state.LocalLevelScreenState
import com.gamovation.core.ui.state.LocalReviewDataHandlerState
import com.gamovation.feature.home.HomeScreen
import com.gamovation.feature.home.HomeScreenViewModel
import com.gamovation.feature.level.LevelScreen
import com.gamovation.feature.level.LevelScreenViewModel
import com.gamovation.feature.menu.MenuScreen
import com.gamovation.feature.menu.MenuViewModel
import com.gamovation.feature.settings.SettingsScreen
import com.gamovation.feature.settings.SettingsScreenViewModel
import com.gamovation.feature.store.StoreScreen
import com.gamovation.feature.store.StoreScreenViewModel

@Composable
fun WordefullNavHost(navController: NavHostController) {
    val context = LocalContext.current

    NavHost(
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
                    .collectAsStateWithLifecycle(initialValue = null).value ?: LevelData(1)

            val idArg = when (levelData.id) {
                19 -> {
                    if (levelData.isCompleted.not()) levelData.id else 1
                }

                else -> levelData.id
            }

            HomeScreen(
                onNavigateToLevel = {
                    navController.navigate(Screens.Level(idArg.toString()))
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

            var errorDialog by remember {
                mutableStateOf(false)
            }
            StoreScreen(
                storeDetails = storeScreenDetails,
                onWatchAd = { viewModel.watchAdReward() },
                errorDialog = errorDialog,
                onBuy = { details, type ->
                    viewModel.purchaseProduct(details, type, onError = {
                        errorDialog = true
                    }) {
                        activity
                    }
                },
                onDismissErrorDialog = {
                    errorDialog = false
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
                mutableIntStateOf(0)
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
                    navController.navigate(Screens.Level(levelId.toString()))
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
                userInteractionState.onIdle()
            }


            val reviewDataHandler = LocalReviewDataHandlerState.current
            LaunchedEffect(reviewDataHandler.isReviewRequested) {

                val state = reviewDataHandler.isReviewRequested
                if (state) {
                    val activity = context as ComponentActivity
                    viewModel.processReviewRequest(activity) {
                        reviewDataHandler.isDialogVisible = true
                    }
                }
            }



            CompositionLocalProvider(
                LocalLevelScreenState provides levelScreenState.value,
                LocalLevelActionState provides levelActionState.value,
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
                                navController.navigate(Screens.Store)
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