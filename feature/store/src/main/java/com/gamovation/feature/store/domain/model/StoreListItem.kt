package com.gamovation.feature.store.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.gamovation.core.billing.model.StoreItemInfo

data class StoreListItem(
    @StringRes val stringRes: Int,
    @DrawableRes val drawableRes: Int,

    @Stable val info: StoreItemInfo? = null
)
