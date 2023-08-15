package com.gamovation.core.navigation


sealed class Screens(val route: String) {
    object Home : Screens(route = "home")
    object Menu : Screens(route = "menu")

    object Store : Screens(route = "store")

    object Settings : Screens(route = "settings")
    data class Level(val id: String = "{id}") : Screens(route = "level?id=$id")
}


fun String.toArg(): String = this.removeSurrounding("{", "}")
