package com.gamovation.feature.level.level15

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
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.level.answers.CounterBlock
import com.gamovation.core.ui.level.interactions.DraggableImage
import com.gamovation.core.ui.theme.WordefullTheme

@Composable
fun Level15Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DrawAnimation {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.l15_pizza),
                        modifier = Modifier.size(96.dp),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                    DraggableImage(
                        modifier = Modifier.size(96.dp),
                        drawableRes = R.drawable.l15_pizza
                    )
                    DraggableImage(
                        modifier = Modifier.size(96.dp),
                        drawableRes = R.drawable.l15_pizza
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
            CounterBlock {
                if (it == 24) {
                    onLevelAction(
                        LevelScreenState.UserCorrectChoice(
                            com.gamovation.core.domain.R.string.event_level_15_finished
                        )
                    )
                } else {
                    onLevelAction(
                        LevelScreenState.UserWrongChoice(
                            com.gamovation.core.domain.R.string.event_level_15_wrong
                        )
                    )
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
