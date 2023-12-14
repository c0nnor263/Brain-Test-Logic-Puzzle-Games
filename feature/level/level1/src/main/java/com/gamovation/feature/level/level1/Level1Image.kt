package com.gamovation.feature.level.level1

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.gamovation.core.ui.animation.DrawAnimation
import kotlin.random.Random

@Composable
internal fun Level1Image(modifier: Modifier = Modifier, index: Int = 0) {
    @DrawableRes val randomDuckImage by remember {
        mutableIntStateOf(
            when (Random.nextInt(0, 3)) {
                0 -> R.drawable.l1_duck
                1 -> R.drawable.l1_duck_1
                else -> R.drawable.l1_duck
            }
        )
    }
    DrawAnimation(modifier = modifier, appearOrder = index) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = randomDuckImage),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}
