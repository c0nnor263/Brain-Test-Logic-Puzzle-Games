package com.gamovation.feature.level

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.database.data.LevelManager
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.Durations
import com.gamovation.feature.level.common.Title

@Composable
fun Final(modifier: Modifier = Modifier) {
    AnimatedVisibility(
        true,
        modifier = modifier.padding(Dimensions.Padding.Small.value),
        enter = fadeIn(tween(Durations.Medium.time)) + scaleIn(tween(Durations.Medium.time)),
        exit = scaleOut(tween(Durations.Medium.time)) + fadeOut(tween(Durations.Medium.time))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.lamp),
                modifier = Modifier.size(64.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
            Title(
                text = stringResource(
                    com.gamovation.feature.level.R.string.level_completed,
                    LevelManager.MAX_LEVEL_ID
                ),
                style = MaterialTheme.typography.displaySmall
            )
            Title(
                text = stringResource(com.gamovation.feature.level.R.string.you_completed_the_game),
                style = MaterialTheme.typography.displaySmall
            )
            Title(
                text = stringResource(com.gamovation.feature.level.R.string.thanks_for_the_playing),
                style = MaterialTheme.typography.displaySmall.copy(color = Color.Yellow)
            )
        }
    }
}
