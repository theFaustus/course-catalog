package inc.evil.coursecatalog.config.kafka

import inc.evil.coursecatalog.InstructorAggregate
import inc.evil.coursecatalog.Key
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG
import io.confluent.kafka.streams.serdes.avro.SpecificAvroDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
@EnableConfigurationProperties(value = [KafkaProperties::class])
class KafkaConfiguration(private val kafkaProperties: KafkaProperties) {
    companion object {
        const val LEGACY_INSTRUCTORS_CONTAINER_FACTORY: String = "legacy-instructors-factory"
    }

    @Bean(LEGACY_INSTRUCTORS_CONTAINER_FACTORY)
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<Key, InstructorAggregate> {
        val factory = ConcurrentKafkaListenerContainerFactory<Key, InstructorAggregate>()
        factory.consumerFactory = DefaultKafkaConsumerFactory(buildConsumerProperties())
        return factory
    }

    private fun buildConsumerProperties(): Map<String, Any> {
        val properties = mutableMapOf<String, Any>()

        properties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        properties[SCHEMA_REGISTRY_URL_CONFIG] = kafkaProperties.schemaRegistryUrl
        properties[ConsumerConfig.GROUP_ID_CONFIG] = kafkaProperties.consumerGroupId
        properties[ConsumerConfig.CLIENT_ID_CONFIG] = kafkaProperties.consumerClientId
        properties[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = kafkaProperties.autoOffsetReset
        properties[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = true

        properties[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = SpecificAvroDeserializer::class.qualifiedName as Any
        properties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = SpecificAvroDeserializer::class.qualifiedName as Any

        return properties
    }
}
