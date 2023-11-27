package com.gamovation.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.gamovation.core.domain.billing.UserVipType

val LocalVipType = compositionLocalOf {
    UserVipType.BASE
}
