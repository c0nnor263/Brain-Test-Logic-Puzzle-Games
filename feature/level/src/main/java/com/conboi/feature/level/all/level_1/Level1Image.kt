package com.conboi.feature.level.all.level_1

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.R
import kotlin.random.Random

@Composable
internal fun Level0Image(modifier: Modifier = Modifier, indexRow: Int = 0, indexColumn: Int = 0) {
    @DrawableRes val randomDuckImage by remember {
        mutableStateOf(
            when (Random.nextInt(0, 3)) {
                0 -> R.drawable.duck
                1 -> R.drawable.duck_1
                else -> R.drawable.duck
            }
        )
    }
    Image(
        modifier = modifier
            .size(128.dp)
            .absoluteOffset(
                x = (indexColumn * -40 * indexRow).dp,
                y = (indexRow * -10 * indexColumn).dp
            ),
        painter = painterResource(id = randomDuckImage),
        contentDescription = stringResource(id = R.string.number_0_duck_content_description),
        contentScale = ContentScale.FillHeight
    )
}