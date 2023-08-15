package com.gamovation.core.domain.level

data class ActionResult(val type: Type, val cost: Int = 0) {
    enum class Type {
        SUCCESS, BUY_MORE, CANCELLED
    }
}

