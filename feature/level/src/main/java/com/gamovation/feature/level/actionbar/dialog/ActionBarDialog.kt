package com.gamovation.feature.level.actionbar.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.domain.level.ActionResult
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.common.ChalkBoardDialog
import com.gamovation.core.ui.state.DialogState
import com.gamovation.core.ui.state.LocalCosts
import com.gamovation.core.ui.state.LocalCurrency
import com.gamovation.core.ui.state.LocalLevelAction
import com.gamovation.core.ui.state.rememberDialogState
import com.gamovation.feature.level.actionbar.dialog.options.ActionBarDialogAdviceOption
import com.gamovation.feature.level.actionbar.dialog.options.ActionBarDialogSkipOption
import com.gamovation.feature.level.common.NotEnoughCurrencyDialog
import com.gamovation.feature.level.domain.model.LevelUiDetails

@Composable
internal fun ActionBarDialog(
    details: LevelUiDetails,
    onActionResult: (ActionResult) -> Unit
) {
    val levelActionState = LocalLevelAction.current
    val actionBarDialogState = rememberDialogState()
    val notEnoughCurrencyDialogState = rememberDialogState()

    LaunchedEffect(levelActionState) {
        if (levelActionState != LevelActionState.IDLE &&
            levelActionState != LevelActionState.RESTART
        ) {
            actionBarDialogState.show()
        } else {
            actionBarDialogState.dismiss()
        }
    }

    ActionBarDialogContent(
        adviceId = details.advise,
        actionBarDialogState = actionBarDialogState,
        levelHasAdvice = details.isHasAdvise,
        onActionResult = onActionResult,
        onNotEnoughCurrency = {
            notEnoughCurrencyDialogState.show()
        }
    )

    NotEnoughCurrencyDialog(
        dialogState = notEnoughCurrencyDialogState,
        onActionResult = {
            notEnoughCurrencyDialogState.dismiss()
            onActionResult(it)
        }
    )
}

@Composable
internal fun ActionBarDialogContent(
    @StringRes adviceId: Int,
    levelHasAdvice: Boolean,
    actionBarDialogState: DialogState,
    onNotEnoughCurrency: () -> Unit,
    onActionResult: (ActionResult) -> Unit
) {
    val levelActionState = LocalLevelAction.current
    val adviceDialogState = rememberDialogState()

    LaunchedEffect(levelActionState, levelHasAdvice) {
        if (levelActionState == LevelActionState.ADVICE && levelHasAdvice) {
            adviceDialogState.show()
            onActionResult(ActionResult(ActionResult.Type.CANCELLED))
        }
    }

    ChalkBoardDialog(
        dialogState = actionBarDialogState,
        onDismissRequest = { onActionResult(ActionResult(ActionResult.Type.CANCELLED)) }
    ) {
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
            ActionBarOptionContent(
                levelHasAdvice = levelHasAdvice,
                onActionResult = onActionResult,
                onNotEnoughCurrency = onNotEnoughCurrency,
                onShowAdvice = {
                    adviceDialogState.show()
                }
            )
        }
    }

    AdviceDialog(
        dialogState = adviceDialogState,
        advice = stringResource(id = adviceId)
    ) {
        adviceDialogState.dismiss()
        onActionResult(ActionResult(ActionResult.Type.CANCELLED))
    }
}

@Composable
internal fun ActionBarOptionContent(
    levelHasAdvice: Boolean,
    onShowAdvice: () -> Unit,
    onActionResult: (ActionResult) -> Unit,
    onNotEnoughCurrency: () -> Unit
) {
    val costsInfo = LocalCosts.current
    val currency = LocalCurrency.current

    when (LocalLevelAction.current) {
        LevelActionState.ADVICE -> {
            ActionBarDialogAdviceOption {
                if (levelHasAdvice) return@ActionBarDialogAdviceOption
                val cost = costsInfo.adviceCost
                val leftCurrency = currency - cost
                if (leftCurrency >= 0) {
                    onActionResult(
                        ActionResult(
                            cost = cost,
                            type = ActionResult.Type.SUCCESS
                        )
                    )
                    onShowAdvice()
                } else {
                    onNotEnoughCurrency()
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
                    onNotEnoughCurrency()
                }
            }
        }

        else -> {}
    }
}
