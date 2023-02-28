package com.conboi.wordefull.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.conboi.wordefull.presentation.host.HostView
import com.conboi.core.navigation.WordefullNavigationKeys

@Composable
fun WordefullAppContent() {
    val navController = rememberNavController()
    HostView(navController = navController) {
        NavHost(
            navController = navController,
            startDestination = WordefullNavigationKeys.Home.route
        ) {
            composable(WordefullNavigationKeys.Home.route) {

            }
        }
    }

}