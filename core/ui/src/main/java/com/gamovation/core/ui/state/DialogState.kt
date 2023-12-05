package com.gamovation.core.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

val DialogStateSaver = Saver<DialogState, Boolean>(
    save = { it.isShowing },
    restore = { DialogState().apply { isShowing = it } }
)

@Composable
fun rememberDialogState(): DialogState {
    return rememberSaveable(saver = DialogStateSaver) {
        DialogState()
    }
}

class DialogState {
    var isShowing by mutableStateOf(false)

    fun show() {
        isShowing = true
    }

    fun dismiss() {
        isShowing = false
    }
}
