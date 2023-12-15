package com.gamovation.feature.level.level7

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.level.answers.NumbersBlock
import com.gamovation.core.ui.level.interactions.DraggableImage

@Composable
fun Level7Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    var isNumberVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.weight(0.2F), contentAlignment = Alignment.Center) {
            Text(
                "15",
                modifier = Modifier.alpha(
                    if (isNumberVisible) 1F else 0F
                )
            )
            DraggableImage(
                drawableRes = R.drawable.l7_dice
            ) { _, _ ->
                isNumberVisible = true
            }
        }
        Spacer(modifier = Modifier.height(Dimensions.Padding.ExtraSmall.value))
        NumbersBlock(modifier = Modifier.weight(1F), numberMaxLength = 2) {
            if (it == "15") {
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_7_finished
                    )
                )
            } else {
                onLevelAction(
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_7_wrong
                    )
                )
            }
        }
    }
}
