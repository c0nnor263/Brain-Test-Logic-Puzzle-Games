package com.conboi.feature.level.all.level_8

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.R
import com.conboi.feature.level.common.answers.NumbersBlock
import com.conboi.feature.level.common.interactions.DraggableImage

@Composable
fun Level8Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.weight(0.15F),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("5 - 15 - 25 -")

            BoxWithConstraints(contentAlignment = Alignment.Center) {
                Text("40")
                DraggableImage(
                    drawableRes = R.drawable.stone,
                    maxSize = Offset(maxWidth.value, maxHeight.value)
                ) {

                }
            }

            Text(text = "- 100")
        }

        NumbersBlock(modifier = Modifier.weight(1F), numberMaxLength = 2) {
            if (it == "40") {
                onLevelAction(LevelScreenState.CORRECT_CHOICE)
            } else {
                onLevelAction(LevelScreenState.WRONG_CHOICE)
            }
        }
    }
}