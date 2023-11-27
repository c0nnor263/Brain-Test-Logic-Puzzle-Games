package com.gamovation.core.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimensions {
    sealed class Padding(val value: Dp) {
        /**
         *  4.dp
         */
        data object ExtraSmall : Padding(4.dp)

        /**
         *  8.dp
         */
        data object Small : Padding(8.dp)

        /**
         *  16.dp
         */
        data object Medium : Padding(16.dp)

        /**
         *  24.dp
         */
        data object Large : Padding(24.dp)

        /**
         *  32.dp
         */
        data object ExtraLarge : Padding(32.dp)

        /**
         *  64.dp
         */
        data object ExtraLarge2X : Padding(64.dp)
    }

    sealed class RoundedShape(val value: Shape) {
        /**
         *  8.dp
         */
        data object Small : RoundedShape(RoundedCornerShape(8.dp))

        /**
         *  16.dp
         */
        data object Medium : RoundedShape(RoundedCornerShape(16.dp))

        /**
         *  24.dp
         */
        data object Large : RoundedShape(RoundedCornerShape(24.dp))

        /**
         *  32.dp
         */
        data object ExtraLarge : RoundedShape(RoundedCornerShape(32.dp))
    }
}
