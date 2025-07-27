package ru.golovanov.mapper

import org.mapstruct.Mapper
import ru.golovanov.dto.ActionMessage
import ru.golovanov.enums.ActionType

@Mapper(
    componentModel = "spring"
)
abstract class ActionMapper {

    fun toPrettyString(message: ActionMessage): String =
        """
            ✅ ${actionEmoji(message.actionType)} ${actionLabel(message.actionType)} в репозитории [${message.repository}]:
            
            🔧 Сообщение: ${message.message}  
            👤 Автор: ${message.author}  
            📅 Время: ${message.time}  
            🔗 ${message.link}
        """.trimIndent()

    private fun actionEmoji(type: ActionType): String = when (type) {
        ActionType.COMMIT -> "✅"
        ActionType.MERGE -> "🔀"
        ActionType.PULL_REQUEST -> "📦"
        ActionType.ISSUE -> "🐞"
        ActionType.RELEASE -> "🚀"
        ActionType.DEFAULT -> "📦"
    }

    private fun actionLabel(type: ActionType): String = when (type) {
        ActionType.COMMIT -> "Новый коммит"
        ActionType.MERGE -> "Слияние веток"
        ActionType.PULL_REQUEST -> "Pull Request"
        ActionType.ISSUE -> "Новая задача"
        ActionType.RELEASE -> "Новый релиз"
        ActionType.DEFAULT -> "Неизвестное действие"
    }
}