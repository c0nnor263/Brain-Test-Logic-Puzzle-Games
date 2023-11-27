package com.gamovation.core.ui.animation

sealed class Durations(val time: Int) {
    /**
     * 100
     */
    data object ShortLight : Durations(100)

    /**
     * 200
     */
    data object Short : Durations(200)

    /**
     * 350
     */
    data object ShortMedium : Durations(350)

    /**
     * 500
     */
    data object Medium : Durations(500)

    /**
     * 800
     */
    data object Long : Durations(800)
}
