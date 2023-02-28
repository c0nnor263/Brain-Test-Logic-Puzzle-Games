package com.conboi.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class WordefullNavigationKeys(val icon: ImageVector, val title:String, val route:String){
    object Home : WordefullNavigationKeys(icon = Icons.Filled.Home , title = "Home", route = "home")
}
