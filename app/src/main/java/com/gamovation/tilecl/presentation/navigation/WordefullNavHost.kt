package com.gamovation.tilecl.presentation.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gamovation.core.navigation.Screens
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.navigate

@Composable
fun WordefullNavHost(navController: NavHostController) {
    NavHost(
        modifier = Modifier
            .fillMaxWidth()
            .clipToBounds()
            .padding(Dimensions.Padding.Small.value),
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        homeScreen(
            onNavigateToLevel = { idArg ->
                navController.navigate(Screens.Level(idArg.toString()))
            }
        )

        storeScreen()

        settingsStore(
            onNavigateToHome = {
                navController.navigate(Screens.Home)
            }
        )

        menuScreen(onNavigateToLevel = { id ->
            navController.navigate(Screens.Level(id.toString()))
        })

        levelScreen(onNavigateToStore = {
            navController.navigate(Screens.Store)
        })
    }
}
