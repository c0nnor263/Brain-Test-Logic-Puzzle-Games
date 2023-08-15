package com.gamovation.core.ui.level

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gamovation.core.domain.level.MAX_LEVEL_ID
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R

@Composable
fun NavigationArrows(
    modifier: Modifier = Modifier,
    currentIndex: Int,
    onIndexUpdate: (Int) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth().animateContentSize(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedVisibility(visible = currentIndex !=0, enter = fadeIn() + scaleIn(), exit = scaleOut() + fadeOut()) {
            Card(
                shape = Dimensions.RoundedShape.ExtraLarge.value,
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            val newId = currentIndex - 5
                            onIndexUpdate(newId.coerceAtLeast(0))
                        }
                        .padding(Dimensions.Padding.Medium.value)
                        .scale(2F),
                    painter = painterResource(id = R.drawable.baseline_arrow_left_24),
                    contentDescription = stringResource(id = R.string.previous_level_button_content_description)
                )
            }
        }

        AnimatedVisibility(visible = currentIndex != MAX_LEVEL_ID - 5, enter = fadeIn() + scaleIn(), exit = scaleOut() + fadeOut()) {
            Card(
                modifier = Modifier.scale(1.1F),
                shape = Dimensions.RoundedShape.ExtraLarge.value,
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            val newId = currentIndex + 5
                            onIndexUpdate(newId.coerceAtMost(MAX_LEVEL_ID - 5))
                        }
                        .padding(Dimensions.Padding.Medium.value)
                        .scale(2F),
                    painter = painterResource(id = R.drawable.baseline_arrow_right_24),
                    contentDescription = stringResource(id = R.string.next_level_button_content_description),
                )
            }
        }
    }

}


@Preview
@Composable
fun NavigationArrowsPreview() {
    NavigationArrows(
        currentIndex = 1,
        onIndexUpdate = {}
    )
}