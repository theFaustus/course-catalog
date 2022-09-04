package inc.evil.coursecatalog.service.impl

import inc.evil.coursecatalog.InstructorAggregate
import inc.evil.coursecatalog.Key
import inc.evil.coursecatalog.config.kafka.KafkaConfiguration
import inc.evil.coursecatalog.service.InstructorService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class LegacyInstructorsSynchronizerListener(
    private val instructorService: InstructorService
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @KafkaListener(
        topics = ["\${kafka.consumer.legacyInstructorsTopic}"],
        containerFactory = KafkaConfiguration.LEGACY_INSTRUCTORS_CONTAINER_FACTORY
    )
    fun handle(
        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) key: Key,
        @Payload instructorAggregate: InstructorAggregate
    ) {
        log.info("Received old instructor [${instructorAggregate}]")
        instructorService.upsert(instructorAggregate)
    }
}
