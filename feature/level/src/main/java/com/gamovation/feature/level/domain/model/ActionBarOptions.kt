package com.gamovation.feature.level.domain.model

import androidx.annotation.DrawableRes
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.ui.R

internal sealed class ActionBarOptions(
    val action: LevelActionState,
    @DrawableRes val icon: Int,
    val cost: Int = 0
) {
    data object Restart : ActionBarOptions(
        action = LevelActionState.RESTART,
        icon = R.drawable.restart_icon
    )

    data class Advice(val adviceCost: Int) : ActionBarOptions(
        action = LevelActionState.ADVICE,
        icon = R.drawable.lamp,
        cost = adviceCost
    )

    data class Skip(val skipCost: Int) : ActionBarOptions(
        action = LevelActionState.SKIP,
        icon = R.drawable.skip_icon,
        cost = skipCost
    )
}
