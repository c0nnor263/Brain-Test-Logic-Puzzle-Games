package com.conboi.feature.level.all.level_1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Dimensions
import com.conboi.feature.level.common.AnswersBlock
import com.conboi.feature.level.common.Title


@Composable
internal fun Level1Content(
    onLevelAction: (LevelUIState) -> Unit
) {
    val listOfAnswers by remember { mutableStateOf(listOf(4, 8, 7, 6).shuffled()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.Padding.Small.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            title = "How many ducks?"
        )

        Spacer(modifier = Modifier.height(64.dp))

        Box(contentAlignment = Alignment.Center) {
            Row {
                repeat(2) { indexColumn ->
                    Level0Image(
                        modifier = Modifier
                            .weight(0.5F, false)
                            .absoluteOffset(x = indexColumn.dp),
                        indexColumn = indexColumn
                    )
                }
            }
            Row {
                repeat(3) { indexColumn ->
                    Level0Image(
                        modifier = Modifier
                            .weight(0.33F, false)
                            .absoluteOffset(x = indexColumn.dp),
                        indexRow = 1,
                        indexColumn = indexColumn + 1
                    )
                }
            }
            Row {
                repeat(3) { indexColumn ->
                    Level0Image(
                        modifier = Modifier
                            .weight(0.33F, false)
                            .absoluteOffset(x = indexColumn.dp),
                        indexRow = 1,
                        indexColumn = indexColumn + 2
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        AnswersBlock(listOfAnswers = listOfAnswers) { answerText ->
            val duckCount = 8.toString()
            val state = if (answerText == duckCount) {
                LevelUIState.COMPLETED
            } else {
                LevelUIState.FAILED
            }
            onLevelAction(state)
        }

    }
}

@Preview
@Composable
fun Level0ContentPreview() {
    Level1Content(onLevelAction = {})
}


