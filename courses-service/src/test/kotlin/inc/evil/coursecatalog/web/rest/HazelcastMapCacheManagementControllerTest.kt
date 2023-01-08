package inc.evil.coursecatalog.web.rest

import inc.evil.coursecatalog.service.impl.HazelcastMapCacheManagerImpl
import inc.evil.coursecatalog.web.dto.OperationResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.ParameterizedTypeReference
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(HazelcastMapCacheManagementController::class)
class HazelcastMapCacheManagementControllerTest {

    private val KEY: String = "key"
    private val CACHE_NAME: String = "cacheName"

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var hazelcastMapCacheManagerImpl: HazelcastMapCacheManagerImpl

    @Test
    fun getDistributedMap_returnsMap() {
        `when`(hazelcastMapCacheManagerImpl.getDistributedMap(CACHE_NAME)).thenReturn(mapOf(KEY to "value"))

        val responseBody = webTestClient.get()
            .uri("/api/v1/hazelcast-cache/distributed/{cacheName}", CACHE_NAME)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(object : ParameterizedTypeReference<Map<Any, Any>>() {})
            .returnResult()
            .responseBody

        Assertions.assertThat(responseBody).containsEntry(KEY, "value")
    }

    @Test
    fun getReplicatedMap_returnsMap() {
        `when`(hazelcastMapCacheManagerImpl.getReplicatedMap(CACHE_NAME)).thenReturn(mapOf(KEY to "value"))

        val responseBody = webTestClient.get()
            .uri("/api/v1/hazelcast-cache/replicated/{cacheName}", CACHE_NAME)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(object : ParameterizedTypeReference<Map<Any, Any>>() {})
            .returnResult()
            .responseBody

        Assertions.assertThat(responseBody).containsEntry(KEY, "value")
    }

    @Test
    fun clearDistributedMap_returnsOperationResponse() {
        `when`(hazelcastMapCacheManagerImpl.clearDistributedMap(CACHE_NAME)).thenReturn(OperationResponse("Everything is nice"))

        val responseBody = webTestClient.delete()
            .uri("/api/v1/hazelcast-cache/distributed/{cacheName}", CACHE_NAME)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(OperationResponse::class.java)
            .returnResult()
            .responseBody

        Assertions.assertThat(responseBody?.message).isEqualTo("Everything is nice")
    }

    @Test
    fun clearReplicatedMap_returnsOperationResponse() {
        `when`(hazelcastMapCacheManagerImpl.clearReplicatedMap(CACHE_NAME)).thenReturn(OperationResponse("Everything is nice"))

        val responseBody = webTestClient.delete()
            .uri("/api/v1/hazelcast-cache/replicated/{cacheName}", CACHE_NAME)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(OperationResponse::class.java)
            .returnResult()
            .responseBody

        Assertions.assertThat(responseBody?.message).isEqualTo("Everything is nice")
    }

    @Test
    fun clearDistributedMapForKey_returnsOperationResponse() {
        `when`(hazelcastMapCacheManagerImpl.clearDistributedMapForKey(KEY, CACHE_NAME)).thenReturn(OperationResponse("Everything is nice"))

        val responseBody = webTestClient.delete()
            .uri("/api/v1/hazelcast-cache/distributed/{cacheName}/{key}", CACHE_NAME, KEY)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(OperationResponse::class.java)
            .returnResult()
            .responseBody

        Assertions.assertThat(responseBody?.message).isEqualTo("Everything is nice")

    }

    @Test
    fun clearReplicatedMapForKey_returnsOperationResponse() {
        `when`(hazelcastMapCacheManagerImpl.clearReplicatedMapForKey(KEY, CACHE_NAME)).thenReturn(OperationResponse("Everything is nice"))

        val responseBody = webTestClient.delete()
            .uri("/api/v1/hazelcast-cache/replicated/{cacheName}/{key}", CACHE_NAME, KEY)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(OperationResponse::class.java)
            .returnResult()
            .responseBody

        Assertions.assertThat(responseBody?.message).isEqualTo("Everything is nice")
    }

}
