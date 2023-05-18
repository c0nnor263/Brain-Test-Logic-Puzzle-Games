package com.conboi.feature.level.all.level_15

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.common.answers.CounterBlock
import com.conboi.feature.level.common.interactions.DraggableImage

@Composable
fun Level15Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.l15_pizza),
                    modifier = Modifier.size(96.dp),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                DraggableImage(
                    modifier = Modifier.size(96.dp),
                    drawableRes = R.drawable.l15_pizza,
                )
                DraggableImage(
                    modifier = Modifier.size(96.dp),
                    drawableRes = R.drawable.l15_pizza,
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
            CounterBlock {
                if (it == 24) {
                    onLevelAction(LevelScreenState.CORRECT_CHOICE)
                } else {
                    onLevelAction(LevelScreenState.WRONG_CHOICE)
                }
            }
        }
    }
}


@Preview
@Composable
fun Level15ContentPreview() {
    WordefullTheme {
        Level15Content(onLevelAction = {})
    }
}