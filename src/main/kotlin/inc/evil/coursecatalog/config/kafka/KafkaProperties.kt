package inc.evil.coursecatalog.config.kafka

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kafka.consumer")
data class KafkaProperties(
    val legacyInstructorsTopic: String,
    val bootstrapServers: String,
    val schemaRegistryUrl: String,
    val applicationId: String,
    val clientId: String,
    val consumerGroupId: String,
    val consumerClientId: String,
    val autoOffsetReset: String,
    val autoCommitInterval: Long
)
