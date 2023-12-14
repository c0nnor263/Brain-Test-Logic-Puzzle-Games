package com.gamovation.feature.level.level8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.gamovation.core.ui.level.answers.NumbersBlock
import com.gamovation.core.ui.level.interactions.DraggableImage

@Composable
fun Level8Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    var isNumberVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.weight(0.15F),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("5 - 15 - 25 -")

            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "40",
                    modifier = Modifier.alpha(if (isNumberVisible) 1F else 0F)
                )
                DraggableImage(
                    drawableRes = R.drawable.l8_stone
                ) { _, _ ->
                    isNumberVisible = true
                }
            }

            Text(text = "- 100")
        }

        NumbersBlock(modifier = Modifier.weight(1F), numberMaxLength = 2) {
            if (it == "40") {
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_8_finished
                    )
                )
            } else {
                onLevelAction(
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_8_wrong
                    )
                )
            }
        }
    }
}
