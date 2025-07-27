package ru.golovanov.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${kafka.producer.send-topic}")
    private val topic: String,
) {

    private val logger = LoggerFactory.getLogger(KafkaProducerService::class.java)

    fun sendActionMessage(message: String) {
        logger.info("Start sending message: {}", message)
        kafkaTemplate.send(topic, message)
        logger.info("Finish sending message")
    }

}