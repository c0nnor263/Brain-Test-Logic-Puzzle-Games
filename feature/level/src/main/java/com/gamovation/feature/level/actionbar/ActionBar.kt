package com.gamovation.feature.level.actionbar

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.animation.tweenTooLong
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.state.LocalCosts
import com.gamovation.core.ui.state.LocalLevelScreen
import com.gamovation.feature.level.domain.model.ActionBarOptions

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    onUpdateLevelActionState: (LevelActionState) -> Unit
) {
    val levelUIState = LocalLevelScreen.current
    val costsInfo = LocalCosts.current
    val actionOptions = remember {
        listOf(
            ActionBarOptions.Restart,
            ActionBarOptions.Advice(costsInfo.adviceCost),
            ActionBarOptions.Skip(costsInfo.skipCost)
        )
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = levelUIState == LevelScreenState.IsLevelPlaying,
        enter = fadeIn(tween(Durations.Short.time)) +
                slideInVertically(tween(Durations.Short.time)) { it },
        exit = slideOutVertically(tween(Durations.Short.time)) { it } +
                fadeOut(tween(Durations.Short.time))
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            actionOptions.forEachIndexed { index, option ->
                ActionOption(
                    index = index,
                    action = option.action,
                    icon = option.icon,
                    cost = option.cost,
                    onUpdateLevelActionState = onUpdateLevelActionState
                )
            }
        }
    }
}

@Composable
internal fun ActionOption(
    index: Int,
    action: LevelActionState,
    @DrawableRes icon: Int,
    cost: Int,
    onUpdateLevelActionState: (LevelActionState) -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tweenTooLong(delayMillis = 3000)
        ),
        label = ""
    )

    val translateAnimation by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 10F,
        animationSpec = infiniteRepeatable(
            animation = tweenTooLong(delayMillis = 2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val scaleAnimation by infiniteTransition.animateFloat(
        initialValue = 0.9F,
        targetValue = 1F,
        animationSpec = infiniteRepeatable(
            animation = tweenTooLong(delayMillis = 3000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(contentAlignment = Alignment.Center) {
        ScalableButton(
            modifier = Modifier
                .padding(Dimensions.Padding.ExtraSmall.value)
                .then(
                    when (action) {
                        LevelActionState.RESTART -> Modifier.rotate(rotateAnimation)
                        LevelActionState.ADVICE -> {
                            Modifier.scale(scaleAnimation)
                        }

                        LevelActionState.SKIP -> Modifier.graphicsLayer {
                            translationX = translateAnimation
                        }

                        else -> Modifier
                    }
                ),
            appearOrder = index,
            onClick = { onUpdateLevelActionState(action) }
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(42.dp)
            )
        }
        Text(
            if (cost > 0) cost.toString() else "",
            modifier = Modifier.align(Alignment.BottomEnd),
            style = MaterialTheme.typography.titleLarge
        )
    }
}
