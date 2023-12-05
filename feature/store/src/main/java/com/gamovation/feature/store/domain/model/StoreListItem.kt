package com.gamovation.feature.store.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gamovation.core.data.model.StoreItemInfo

data class StoreListItem(
    @StringRes val stringRes: Int,
    @DrawableRes val drawableRes: Int,
    val info: StoreItemInfo? = null
)
