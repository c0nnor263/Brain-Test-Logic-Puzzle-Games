package com.conboi.feature.level.advice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.currency.DEFAULT_ADVICE_COST
import com.conboi.core.domain.level.ActionResult
import com.conboi.core.domain.level.LevelActionState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.animation.DrawAnimation
import com.conboi.core.ui.common.ChalkBoardCard
import com.conboi.core.ui.state.LocalCurrency
import com.conboi.feature.level.common.NotEnoughCurrencyDialog

@Composable
fun GetAdviceDialog(
    levelActionState: LevelActionState?,
    levelData: LevelData,
    onActionResult: (ActionResult) -> Unit
) {
    val currency = LocalCurrency.current
    var showNotEnoughCurrency by rememberSaveable {
        mutableStateOf(false)
    }

    var showAdvice by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(levelActionState, levelData.isHasAdvise) {
        if (levelActionState == LevelActionState.ADVICE && levelData.isHasAdvise) {
            showAdvice = true
        }
    }

    AnimatedVisibility(
        visible = levelActionState == LevelActionState.ADVICE && !levelData.isHasAdvise,
        enter =
        scaleIn(tween(Durations.Short.time)) +
                expandVertically(tween(Durations.Short.time)) { it } + expandHorizontally(
            tween(
                Durations.ShortMedium.time
            )
        ) { it },
        exit =
        shrinkVertically(tween(Durations.Short.time)) { it } + shrinkHorizontally(
            tween(
                Durations.ShortMedium.time
            )
        ) { it } + scaleOut(tween(Durations.Short.time)),
    ) {


        Dialog(onDismissRequest = { onActionResult(ActionResult.CANCELLED) }) {
            ChalkBoardCard {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lamp),
                        modifier = Modifier.size(96.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))
                    DrawAnimation {
                        Text(
                            text = "Nothing else pops into your head?",
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                    DrawAnimation(delay = Durations.Medium.time.toLong()) {
                        Text(
                            text = "Well, you can get a hint",
                            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Yellow),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))

                    DrawAnimation(delay = Durations.Medium.time.toLong() * 2) {
                        Text(text = "Get hint!", modifier = Modifier.clickable {
                            val leftCurrency = currency - DEFAULT_ADVICE_COST
                            if (leftCurrency >= 0) {
                                onActionResult(ActionResult.SUCCESS)
                                showAdvice = true
                            } else {
                                showNotEnoughCurrency = true
                            }
                        }, style = MaterialTheme.typography.displaySmall)
                    }
                }
            }
        }
    }

    NotEnoughCurrencyDialog(
        visible = showNotEnoughCurrency,
        onLevelActionResult = {
            showNotEnoughCurrency = false
            onActionResult(it)
        }
    )


    AdviceDialog(visible = showAdvice, advice = levelData.advise) {
        showAdvice = false
        onActionResult(ActionResult.CANCELLED)
    }

}
