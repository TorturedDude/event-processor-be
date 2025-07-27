package ru.golovanov.dto

import ru.golovanov.enums.ActionType
import java.time.LocalDateTime

data class ActionMessage(
    val actionType: ActionType,
    val repository: String,
    val message: String,
    val author: String,
    val time: LocalDateTime,
    val link: String,
)
