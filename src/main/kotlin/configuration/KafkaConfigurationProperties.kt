package ru.golovanov.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka")
data class KafkaConfigurationProperties(
    val bootstrapServers: String,
    val groupId: String,
)
