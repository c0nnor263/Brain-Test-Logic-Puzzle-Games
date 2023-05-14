package com.conboi.feature.level.all.level_7

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.R
import com.conboi.feature.level.common.answers.NumbersBlock
import com.conboi.feature.level.common.interactions.DraggableImage

@Composable
fun Level7Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    var isNumberVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(modifier = Modifier.weight(0.2F), contentAlignment = Alignment.Center) {
            Text(
                "15", modifier = Modifier.alpha(
                    if (isNumberVisible) 1F else 0F
                )
            )
            DraggableImage(
                drawableRes = R.drawable.dice
            ) { _, _ ->
                isNumberVisible = true
            }
        }

        NumbersBlock(modifier = Modifier.weight(1F), numberMaxLength = 2) {
            if (it == "15") {
                onLevelAction(LevelScreenState.CORRECT_CHOICE)
            } else {
                onLevelAction(LevelScreenState.WRONG_CHOICE)
            }
        }
    }
}