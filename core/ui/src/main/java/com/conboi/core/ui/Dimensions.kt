package com.conboi.core.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class Padding(
    val extraSmall: Dp = 8.dp,
    val small: Dp = 16.dp,
    val medium: Dp = 32.dp,
    val large: Dp = 48.dp,
    val extraLarge: Dp = 64.dp,
    val extraLarge2X: Dp = 96.dp,
)

val sw892dp = Padding()
val sw700dp = Padding(
    extraSmall = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    large = 32.dp,
    extraLarge = 48.dp,
    extraLarge2X = 64.dp,
)
val sw600dp = Padding(
    extraSmall = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    large = 24.dp,
    extraLarge = 32.dp,
    extraLarge2X = 48.dp,
)


object Dimensions {
    sealed class Padding(val value: Dp) {
        /**
         *  4.dp
         */
        object ExtraSmall : Padding(4.dp)

        /**
         *  8.dp
         */
        object Small : Padding(8.dp)

        /**
         *  16.dp
         */
        object Medium : Padding(16.dp)

        /**
         *  24.dp
         */
        object Large : Padding(24.dp)

        /**
         *  32.dp
         */
        object ExtraLarge : Padding(32.dp)

        /**
         *  64.dp
         */
        object ExtraLarge2X : Padding(64.dp)
    }

    sealed class RoundedShape(val value: Shape) {
        /**
         *  8.dp
         */
        object Small : RoundedShape(RoundedCornerShape(8.dp))

        /**
         *  16.dp
         */
        object Medium : RoundedShape(RoundedCornerShape(16.dp))

        /**
         *  24.dp
         */
        object Large : RoundedShape(RoundedCornerShape(24.dp))

        /**
         *  32.dp
         */
        object ExtraLarge : RoundedShape(RoundedCornerShape(32.dp))
    }
}
