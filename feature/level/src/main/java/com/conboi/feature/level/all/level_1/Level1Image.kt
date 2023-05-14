package com.conboi.feature.level.all.level_1

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.R
import com.conboi.core.ui.animation.DrawAnimation
import kotlin.random.Random

@Composable
internal fun Level1Image(modifier: Modifier = Modifier, index: Int = 0) {
    @DrawableRes val randomDuckImage by remember {
        mutableStateOf(
            when (Random.nextInt(0, 3)) {
                0 -> R.drawable.duck
                1 -> R.drawable.duck_1
                else -> R.drawable.duck
            }
        )
    }
    DrawAnimation(modifier = modifier, delayOrder = index) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(id = randomDuckImage),
            contentDescription = stringResource(id = R.string.number_0_duck_content_description)
        )
    }
}