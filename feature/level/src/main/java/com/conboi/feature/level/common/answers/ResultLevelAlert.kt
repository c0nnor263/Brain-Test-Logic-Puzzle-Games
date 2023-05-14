package com.conboi.feature.level.common.answers

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R

@Composable
fun BoxScope.ResultLevelAlert(currentState: LevelScreenState, checkState: LevelScreenState) {
    @DrawableRes val drawableRes = when (checkState) {
        LevelScreenState.WRONG_CHOICE -> R.drawable.failure
        LevelScreenState.CORRECT_CHOICE -> R.drawable.success
        else -> R.drawable.failure
    }

    @StringRes val contentDescription = when (checkState) {
        LevelScreenState.WRONG_CHOICE -> R.string.failure_image_content_description
        LevelScreenState.CORRECT_CHOICE -> R.string.success_image_content_description
        else -> R.string.failure_image_content_description
    }


    AnimatedVisibility(
        currentState == checkState,
        modifier = Modifier.matchParentSize(),
        enter = fadeIn(tween(Durations.Medium.time)) + scaleIn(tween(Durations.Medium.time)),
        exit = scaleOut(tween(Durations.Medium.time)) + fadeOut(tween(Durations.Medium.time)),
    ) {
        Image(
            modifier = Modifier.padding(Dimensions.Padding.ExtraLarge2X.value),
            painter = painterResource(id = drawableRes),
            contentDescription = stringResource(id = contentDescription),
            contentScale = ContentScale.Fit
        )
    }
}


