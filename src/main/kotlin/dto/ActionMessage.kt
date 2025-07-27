package ru.golovanov.dto

import ru.golovanov.enums.ActionType
import java.time.LocalDateTime

data class ActionMessage(
    val actionType: ActionType,
    val repository: String,
    val message: String,
    val branch: String,
    val author: String,
    val time: String,
    val link: String,
)
