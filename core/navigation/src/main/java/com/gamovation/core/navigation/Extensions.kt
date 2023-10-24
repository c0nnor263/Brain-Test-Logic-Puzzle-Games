package com.gamovation.core.navigation

fun String.toArg(): String = this.removeSurrounding("{", "}")