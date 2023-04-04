package com.conboi.feature.level.all.level_3

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.feature.level.common.SunDraggable
import com.conboi.feature.level.common.Title

@Composable
internal fun Level3Content(onLevelAction: (LevelUIState) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    var isIceCreamMelted by rememberSaveable { mutableStateOf(false) }
    var isMelting by rememberSaveable { mutableStateOf(false) }

    val iceCreamScaleAnimation by animateFloatAsState(
        targetValue = if (isMelting) 0.75F else 1F,
        animationSpec = tween(Durations.Long.time),
        label = ""
    ) {
        isIceCreamMelted = true
    }
    val flyScaleAnimation by animateFloatAsState(
        targetValue = if (isMelting) 1F else 0F,
        animationSpec = tween(Durations.Long.time),
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.Padding.Small.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            title = "Which one is not ice cream?",
        )
        Spacer(modifier = Modifier.height(32.dp))

        BoxWithConstraints(contentAlignment = Alignment.Center) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimensions.Padding.ExtraLarge.value),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                repeat(2) { repeatRowIndex ->
                    Row {
                        repeat(3) { repeatItemIndex ->
                            Box(
                                modifier = Modifier
                                    .weight(0.33F)
                                    .scale(scaleX = 1F, scaleY = iceCreamScaleAnimation)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        enabled = isIceCreamMelted,
                                    ) {
                                        val newState =
                                            if (isIceCreamMelted && repeatItemIndex == 1 && repeatRowIndex == 1) {
                                                LevelUIState.COMPLETED
                                            } else LevelUIState.FAILED
                                        onLevelAction(newState)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ice_cream),
                                    contentDescription = null
                                )
                                if (repeatItemIndex == 1 && repeatRowIndex == 1) {
                                    Image(
                                        modifier = Modifier.scale(flyScaleAnimation),
                                        painter = painterResource(id = R.drawable.fly),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }

            SunDraggable(
                modifier = Modifier.align(Alignment.TopStart),
                initialOffset = Offset(-256F, -128F),
                isEnabled = !isIceCreamMelted
            ) { sunOffset ->
                if (sunOffset.x >= maxWidth.value) {
                    isMelting = true
                }
            }
        }
    }
}

@Preview
@Composable
fun Level3ContentPreview() {
    Level3Content(onLevelAction = {})
}