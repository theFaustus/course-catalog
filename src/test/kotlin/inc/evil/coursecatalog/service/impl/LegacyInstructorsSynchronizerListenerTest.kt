package inc.evil.coursecatalog.service.impl

import inc.evil.coursecatalog.InstructorAggregate
import inc.evil.coursecatalog.Key
import inc.evil.coursecatalog.common.AbstractTestcontainersIntegrationTest
import inc.evil.coursecatalog.service.InstructorService
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig
import io.confluent.kafka.serializers.subject.TopicNameStrategy
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.KafkaAdminClient
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Test
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.time.Instant
import java.util.*


@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class LegacyInstructorsSynchronizerListenerTest : AbstractTestcontainersIntegrationTest() {

    @MockBean
    private lateinit var instructorService: InstructorService

    @Test
    fun handle() {
        val instructorAggregate = InstructorAggregate(1, Instant.ofEpochMilli(1000), Instant.ofEpochMilli(1000), "Mike Scott")

        producer().send(ProducerRecord("legacy-instructors", Key(1), instructorAggregate)).get()

        verify(instructorService, timeout(10000)).upsert(instructorAggregate)
    }

    private fun adminClient(): AdminClient {
        val properties = Properties()
        properties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafka.bootstrapServers
        return KafkaAdminClient.create(properties)
    }

    private fun producer(): KafkaProducer<Key, InstructorAggregate> {
        val props = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafka.bootstrapServers
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.RETRIES_CONFIG] = 0
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = SpecificAvroSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = SpecificAvroSerializer::class.java
        props[KafkaAvroSerializerConfig.VALUE_SUBJECT_NAME_STRATEGY] = TopicNameStrategy::class.java.name
        props[AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = "mock://testUrl"
        props[ProducerConfig.CLIENT_ID_CONFIG] = "legacy-instructors-producer";
        return KafkaProducer<Key, InstructorAggregate>(props)
    }
}
