package com.gamovation.core.navigation


sealed class Screens(val route: String) {
    data object Home : Screens(route = "home")
    data object Menu : Screens(route = "menu")

    data object Store : Screens(route = "store")

    data object Settings : Screens(route = "settings")
    data class Level(val id: String = "{id}") : Screens(route = "level?id=$id")
}


fun String.toArg(): String = this.removeSurrounding("{", "}")
