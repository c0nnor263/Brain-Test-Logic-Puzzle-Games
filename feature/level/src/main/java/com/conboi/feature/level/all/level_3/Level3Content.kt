package com.conboi.feature.level.all.level_3

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Dimensions
import com.conboi.feature.level.common.LocalLevelUIState
import com.conboi.feature.level.common.SunDraggable

@Composable
internal fun Level3Content(modifier: Modifier = Modifier, onLevelAction: (LevelUIState) -> Unit) {
    val levelUIState = LocalLevelUIState.current
    var isMelting by rememberSaveable { mutableStateOf(false) }
    val meltingTransition = updateTransition(targetState = isMelting, label = "")

    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimensions.Padding.ExtraLarge.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            repeat(2) { repeatRowIndex ->
                Row(
                    modifier = Modifier
                        .weight(0.5F)
                        .fillMaxWidth()
                        .padding(vertical = Dimensions.Padding.Small.value),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(3) { repeatItemIndex ->


                        Level3IceCream(
                            modifier = Modifier.weight(0.33F),
                            index = 1 + repeatItemIndex + repeatRowIndex * 3,
                            transition = meltingTransition,
                            isNotIceCream = repeatItemIndex == 1 && repeatRowIndex == 1
                        ) {
                            if (levelUIState != LevelUIState.PROCESSING) return@Level3IceCream
                            val newState =
                                if (meltingTransition.currentState && repeatItemIndex == 1 && repeatRowIndex == 1) {
                                    LevelUIState.COMPLETED
                                } else LevelUIState.FAILED
                            onLevelAction(newState)
                        }
                    }
                    }
                }
            }

            SunDraggable(
                modifier = Modifier.align(Alignment.TopStart),
                initialOffset = Offset(0F, -128F),
                isEnabled = !meltingTransition.currentState
            ) { sunOffset ->
                if (sunOffset.x >= maxWidth.value) {
                    isMelting = true
                }
            }
    }
}

@Preview
@Composable
fun Level3ContentPreview() {
    Level3Content(onLevelAction = {})
}