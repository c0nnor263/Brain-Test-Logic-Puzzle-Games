package com.conboi.core.ui.level

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R

@Composable
fun NavigationArrows(
    modifier: Modifier = Modifier,
    currentIndex: Int,
    maxIndex: Int,
    onIndexUpdate: (Int) -> Unit,
    content: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = Dimensions.RoundedShape.ExtraLarge.value
        ) {
            Icon(
                modifier = Modifier
                    .clickable {
                        val newId = currentIndex - 1
                        onIndexUpdate(newId.coerceAtLeast(1))
                    }
                    .padding(Dimensions.Padding.ExtraSmall.value),
                painter = painterResource(id = R.drawable.baseline_arrow_left_24),
                contentDescription = stringResource(id = R.string.previous_level_button_content_description)
            )
        }
        content?.invoke()
        Card(
            shape = Dimensions.RoundedShape.ExtraLarge.value
        ) {
            Icon(
                modifier = Modifier
                    .clickable {
                        val newId = currentIndex + 1
                        onIndexUpdate(newId.coerceAtMost(maxIndex))
                    }
                    .padding(Dimensions.Padding.ExtraSmall.value),
                painter = painterResource(id = R.drawable.baseline_arrow_right_24),
                contentDescription = stringResource(id = R.string.next_level_button_content_description)
            )
        }
    }

}