package com.conboi.feature.level.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import kotlin.random.Random

@Composable
internal fun OptionButton(
    modifier: Modifier = Modifier, index: Int, text: String, onClick: () -> Unit
) {
    @DrawableRes val randomButtonBackground by remember {
        mutableStateOf(
            when (Random.nextInt(0, 3)) {
                0 -> R.drawable.button_green
                1 -> R.drawable.button_yellow
                else -> R.drawable.button_green
            }
        )
    }


    Card(
        modifier = modifier.clickable { onClick() },
        shape = Dimensions.RoundedShape.Medium.value
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = randomButtonBackground),
                contentDescription = stringResource(
                    id = R.string.option_button_content_description, index
                ),
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
            )
        }

    }
}