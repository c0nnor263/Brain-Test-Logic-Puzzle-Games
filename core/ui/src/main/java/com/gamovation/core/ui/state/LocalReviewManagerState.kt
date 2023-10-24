package com.gamovation.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val LocalReviewDataHandlerState = compositionLocalOf<ReviewDataHandler> {
    ReviewDataHandler()
}


class ReviewDataHandler {
    var isReviewRequested by mutableStateOf(false)
    var isDialogVisible by mutableStateOf(false)
}