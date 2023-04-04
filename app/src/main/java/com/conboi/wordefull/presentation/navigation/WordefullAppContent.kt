package com.conboi.wordefull.presentation.navigation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.conboi.core.navigation.Screens
import com.conboi.core.navigation.toArg
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.defaultSpringAnimation
import com.conboi.feature.home.HomeScreen
import com.conboi.feature.home.HomeScreenViewModel
import com.conboi.feature.level.LevelScreen
import com.conboi.feature.level.LevelScreenViewModel
import com.conboi.feature.menu.MenuScreen
import com.conboi.feature.settings.SettingsScreen
import com.conboi.wordefull.presentation.composables.BottomBarContent
import com.conboi.wordefull.presentation.composables.header_bar.HeaderBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordefullAppContent() {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomBarContent()
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                ),
            color = Color.Transparent
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(Dimensions.Padding.ExtraSmall.value),
                    colors = CardDefaults.cardColors(Color.White),
                    shape = Dimensions.RoundedShape.Large.value
                ) {
                    HeaderBar(navController = navController)
                    Divider(Modifier.fillMaxWidth(), thickness = 2.dp, Color.Black)

                    NavHost(
                        modifier = Modifier.animateContentSize(defaultSpringAnimation()),
                        navController = navController,
                        startDestination = Screens.Home.route
                    ) {
                        composable(Screens.Home.route) {
                            val viewModel: HomeScreenViewModel = hiltViewModel()

                            HomeScreen(
                                viewModel = viewModel,
                                onNavigateToLevel = { levelId: Int ->
                                    navController.navigate(Screens.Level(levelId.toString()).route)
                                }
                            )
                        }

                        composable(Screens.Settings.route) {
                            SettingsScreen()
                        }

                        composable(Screens.Menu.route) {
                            MenuScreen(
                                onNavigateToLevel = { levelId: Int ->
                                    navController.navigate(Screens.Level(levelId.toString()).route)
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
                                }
                            )
                        ) { backStackEntry ->
                            val viewModel: LevelScreenViewModel = hiltViewModel()
                            val idArg = backStackEntry.arguments?.getInt(idArgName) ?: 1
                            LevelScreen(entryLevelId = idArg, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun WordefullAppContentPreview() {

    WordefullAppContent()
}