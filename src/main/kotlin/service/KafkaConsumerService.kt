package ru.golovanov.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.golovanov.dto.ActionMessage
import ru.golovanov.mapper.ActionMapper

@Service
class KafkaConsumerService(
    private val mapper: ActionMapper,
    private val producerService: KafkaProducerService,
) {

    private val logger = LoggerFactory.getLogger(KafkaConsumerService::class.java)

    @KafkaListener(topics = ["\${kafka.consumer.read-topic}"])
    fun readTopic(actionMessage: ActionMessage) {
        logger.info("recivied message {}", actionMessage)
        producerService.sendActionMessage(mapper.toPrettyString(actionMessage))
    }

}