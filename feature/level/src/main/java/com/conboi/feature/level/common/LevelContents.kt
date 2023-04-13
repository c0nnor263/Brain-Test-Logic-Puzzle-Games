package com.conboi.feature.level.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import com.conboi.core.domain.ui.LevelActionState
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Dimensions
import com.conboi.feature.level.all.level_1.Level1Content
import com.conboi.feature.level.all.level_2.Level2Content
import com.conboi.feature.level.all.level_3.Level3Content
import com.conboi.feature.level.all.level_4.Level4Content
import com.conboi.feature.level.all.level_5.Level5Content
import kotlinx.coroutines.delay

@Composable
fun LevelContents(
    modifier: Modifier = Modifier,
    currentLevelId: Int,
    isRestarting: Boolean,
    onLevelAction: (LevelActionState) -> Unit,
    onLevelUIAction: (LevelUIState) -> Unit
) {
    val levelUIState = LocalLevelUIState.current
    var restartingBegan by rememberSaveable { mutableStateOf(false) }
    var parentBoxSize by remember { mutableStateOf(IntSize.Zero) }



    Box(
        modifier = modifier.onSizeChanged {
            if (!restartingBegan) {
                parentBoxSize = it
            }
        },
        contentAlignment = Alignment.Center
    ) {
        // TODO Locked state LevelData

        when {
            restartingBegan -> {
                Box(
                    modifier = Modifier.then(
                        with(LocalDensity.current) {
                            Modifier.size(
                                width = parentBoxSize.width.toDp(),
                                height = parentBoxSize.height.toDp(),
                            )
                        }
                    )
                ) {}
            }

            levelUIState == LevelUIState.WAITING -> {
                LevelCompleted(onLevelUIAction = onLevelUIAction)
            }

            else -> {
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Title(currentLevelId = currentLevelId)

                    Spacer(modifier = Modifier.height(Dimensions.Padding.ExtraLarge.value))

                    when (currentLevelId) {
                        1 -> Level1Content(modifier = modifier, onLevelAction = onLevelUIAction)
                        2 -> Level2Content(modifier = modifier, onLevelAction = onLevelUIAction)
                        3 -> Level3Content(modifier = modifier, onLevelAction = onLevelUIAction)
                        4 -> Level4Content(modifier = modifier, onLevelAction = onLevelUIAction)
                        5 -> Level5Content(modifier = modifier, onLevelAction = onLevelUIAction)
                        else -> {}
                    }
                }
            }
        }

        LevelRestarting(modifier = Modifier.matchParentSize(), visible = isRestarting)
        LaunchedEffect(isRestarting) {
            if (isRestarting) {
                delay(250)
                restartingBegan = true
                delay(250)
                restartingBegan = false
                onLevelAction(LevelActionState.IDLE)
            }
        }
    }
}