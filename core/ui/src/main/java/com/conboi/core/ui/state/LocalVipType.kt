package com.conboi.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.conboi.core.domain.billing.UserVipType

val LocalVipType = compositionLocalOf {
    UserVipType.BASE
}