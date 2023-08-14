package com.gamovation.feature.level.action_bar.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.domain.level.ActionResult
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.core.ui.state.LocalCosts
import com.gamovation.core.ui.state.LocalCurrency
import com.gamovation.core.ui.state.LocalLevelActionState
import com.gamovation.feature.level.action_bar.dialog.options.ActionBarDialogAdviceOption
import com.gamovation.feature.level.action_bar.dialog.options.ActionBarDialogSkipOption
import com.gamovation.feature.level.common.NotEnoughCurrencyDialog

@Composable
fun ActionBarDialog(
    levelData: LevelData,
    onActionResult: (ActionResult) -> Unit
) {
    val costsInfo = LocalCosts.current
    val levelActionState = LocalLevelActionState.current
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
            onActionResult(ActionResult(ActionResult.Type.CANCELLED))
        }
    }

    AnimatedVisibility(
        visible =
        levelActionState != LevelActionState.IDLE &&
                levelActionState != LevelActionState.RESTART,
    ) {


        Dialog(onDismissRequest = { onActionResult(ActionResult(ActionResult.Type.CANCELLED)) }) {
            ChalkBoardCard {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lamp),
                        modifier = Modifier.size(96.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))
                    when (levelActionState) {
                        LevelActionState.ADVICE -> {
                            ActionBarDialogAdviceOption {
                                if (levelData.isHasAdvise) {
                                    return@ActionBarDialogAdviceOption
                                }
                                val cost = costsInfo.adviceCost
                                val leftCurrency = currency - cost
                                if (leftCurrency >= 0) {
                                    onActionResult(
                                        ActionResult(
                                            cost = cost,
                                            type = ActionResult.Type.SUCCESS
                                        )
                                    )
                                    showAdvice = true
                                } else {
                                    showNotEnoughCurrency = true
                                }
                            }
                        }

                        LevelActionState.SKIP -> {
                            ActionBarDialogSkipOption {
                                val cost = costsInfo.skipCost
                                val leftCurrency = currency - cost
                                if (leftCurrency >= 0) {
                                    onActionResult(
                                        ActionResult(
                                            cost = cost,
                                            type = ActionResult.Type.SUCCESS
                                        )
                                    )
                                } else {
                                    showNotEnoughCurrency = true
                                }
                            }
                        }

                        else -> {}
                    }

                }
            }
        }
    }

    NotEnoughCurrencyDialog(
        visible = showNotEnoughCurrency,
        onActionResult = {
            showNotEnoughCurrency = false
            onActionResult(it)
        }
    )


    AdviceDialog(visible = showAdvice, advice = levelData.advise) {
        showAdvice = false
        onActionResult(ActionResult(ActionResult.Type.CANCELLED))
    }

}