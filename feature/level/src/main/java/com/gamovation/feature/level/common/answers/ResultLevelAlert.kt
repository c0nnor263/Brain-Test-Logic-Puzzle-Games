package com.gamovation.feature.level.common.answers

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.Durations
import com.gamovation.feature.level.R

@Composable
fun BoxScope.ResultLevelAlert(currentState: LevelScreenState, checkState: LevelScreenState) {
    @DrawableRes val drawableRes = when (checkState) {
        LevelScreenState.WRONG_CHOICE -> R.drawable.failure
        LevelScreenState.CORRECT_CHOICE -> R.drawable.success
        else -> R.drawable.failure
    }

    AnimatedVisibility(
        currentState == checkState,
        modifier = Modifier.matchParentSize(),
        enter = fadeIn(tween(Durations.Medium.time)) + scaleIn(tween(Durations.Medium.time)),
        exit = scaleOut(tween(Durations.Medium.time)) + fadeOut(tween(Durations.Medium.time))
    ) {
        Image(
            modifier = Modifier.padding(Dimensions.Padding.ExtraLarge2X.value),
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}
