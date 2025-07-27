package ru.golovanov.mapper

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import ru.golovanov.dto.ActionMessage
import ru.golovanov.enums.ActionType
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Mapper(
    componentModel = "spring"
)
abstract class ActionMapper {

    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm", Locale("ru"))

    fun toPrettyString(message: ActionMessage): String {
        val formattedTime = message.time.format(formatter)

        return """
            ✅ ${actionEmoji(message.actionType)} ${actionLabel(message.actionType)} в репозитории [${message.repository}]:
            
            🔧 Сообщение: ${message.message}  
            👤 Автор: ${message.author}  
            📅 Время: $formattedTime  
            🔗 ${message.link}
        """.trimIndent()
    }

    private fun actionEmoji(type: ActionType): String = when (type) {
        ActionType.COMMIT -> "✅"
        ActionType.MERGE -> "🔀"
        ActionType.PULL_REQUEST -> "📦"
        ActionType.ISSUE -> "🐞"
        ActionType.RELEASE -> "🚀"
    }

    private fun actionLabel(type: ActionType): String = when (type) {
        ActionType.COMMIT -> "Новый коммит"
        ActionType.MERGE -> "Слияние веток"
        ActionType.PULL_REQUEST -> "Pull Request"
        ActionType.ISSUE -> "Новая задача"
        ActionType.RELEASE -> "Новый релиз"
    }
}