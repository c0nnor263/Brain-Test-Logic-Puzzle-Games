package com.gamovation.feature.level.all.level1

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.feature.level.R
import kotlin.random.Random

@Composable
internal fun Level1Image(modifier: Modifier = Modifier, index: Int = 0) {
    @DrawableRes val randomDuckImage by remember {
        mutableStateOf(
            when (Random.nextInt(0, 3)) {
                0 -> R.drawable.l1_duck
                1 -> R.drawable.l1_duck_1
                else -> R.drawable.l1_duck
            }
        )
    }
    DrawAnimation(modifier = modifier, delayOrder = index) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(id = randomDuckImage),
            contentDescription = null
        )
    }
}
