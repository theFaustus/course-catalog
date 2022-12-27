package inc.evil.coursecatalog.common

import org.junit.jupiter.api.BeforeAll
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName

abstract class AbstractKafkaTestcontainersIntegrationTest {

    companion object {

        val kafka: KafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))
            .withEmbeddedZookeeper()

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("kafka.consumer.bootstrap-servers", kafka::getBootstrapServers)
        }

        @JvmStatic
        @BeforeAll
        internal fun setUp(): Unit {
            kafka.start()
        }

    }

}
