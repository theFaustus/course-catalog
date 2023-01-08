package inc.evil.coursecatalog.service.impl

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.map.IMap
import com.hazelcast.replicatedmap.ReplicatedMap
import inc.evil.coursecatalog.service.HazelcastMapCacheManager
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class HazelcastMapCacheManagerImplTest {

    private val CACHE_NAME: String = "cacheName"
    private val hazelcastInstance: HazelcastInstance = Mockito.mock(HazelcastInstance::class.java)
    private val hazelcastMapCacheManager: HazelcastMapCacheManager = HazelcastMapCacheManagerImpl(hazelcastInstance)
    private val distributedMap = Mockito.mock(IMap::class.java) as IMap<Any, Any>
    private val replicatedMap = Mockito.mock(ReplicatedMap::class.java) as ReplicatedMap<Any, Any>

    @BeforeEach
    fun setUp() {
        `when`(hazelcastInstance.getMap<Any, Any>(CACHE_NAME)).thenReturn(distributedMap)
        `when`(hazelcastInstance.getReplicatedMap<Any, Any>(CACHE_NAME)).thenReturn(replicatedMap)
    }

    @Test
    fun getDistributedMap_callsHazelcastInstance() {
        hazelcastMapCacheManager.getDistributedMap(CACHE_NAME)

        verify(hazelcastInstance).getMap<Any, Any>(CACHE_NAME)
    }

    @Test
    fun getReplicatedMap_callsHazelcastInstance() {
        hazelcastMapCacheManager.getReplicatedMap(CACHE_NAME)

        verify(hazelcastInstance).getReplicatedMap<Any, Any>(CACHE_NAME)
    }

    @Test
    fun getDistributedMap_whenHazelcastInstanceException_returnsEmptyMap() {
        `when`(hazelcastInstance.getMap<Any, Any>(CACHE_NAME)).thenThrow(RuntimeException())

        Assertions.assertThat(hazelcastMapCacheManager.getDistributedMap(CACHE_NAME)).isEmpty()

        verify(hazelcastInstance).getMap<Any, Any>(CACHE_NAME)
    }

    @Test
    fun getReplicatedMap_whenHazelcastInstanceException_returnsEmptyMap() {
        `when`(hazelcastInstance.getReplicatedMap<Any, Any>(CACHE_NAME)).thenThrow(RuntimeException())

        Assertions.assertThat(hazelcastMapCacheManager.getReplicatedMap(CACHE_NAME)).isEmpty()

        verify(hazelcastInstance).getReplicatedMap<Any, Any>(CACHE_NAME)
    }

    @Test
    fun clearDistributedMap_callsHazelcastInstance() {
        Assertions.assertThat(hazelcastMapCacheManager.clearDistributedMap(CACHE_NAME).message).isEqualTo("IMap[cacheName] cleared successfully")
        verify(hazelcastInstance).getMap<Any, Any>(CACHE_NAME)
        verify(distributedMap).clear()
    }

    @Test
    fun clearReplicatedMap_callsHazelcastInstance() {
        Assertions.assertThat(hazelcastMapCacheManager.clearReplicatedMap(CACHE_NAME).message).isEqualTo("ReplicatedMap[cacheName] cleared successfully")
        verify(hazelcastInstance).getReplicatedMap<Any, Any>(CACHE_NAME)
        verify(replicatedMap).clear()
    }

    @Test
    fun clearDistributedMap_whenHazelcastException_returnsErrorMessage() {
        `when`(hazelcastInstance.getMap<Any, Any>(CACHE_NAME)).thenThrow(RuntimeException("Oops"))

        Assertions.assertThat(hazelcastMapCacheManager.clearDistributedMap(CACHE_NAME).message)
            .isEqualTo("Oops! Something went wrong during clearing IMap[cacheName]... Oops")
        verify(hazelcastInstance).getMap<Any, Any>(CACHE_NAME)
    }

    @Test
    fun clearReplicatedMap_whenHazelcastException_returnsErrorMessage() {
        `when`(hazelcastInstance.getReplicatedMap<Any, Any>(CACHE_NAME)).thenThrow(RuntimeException("Oops"))

        Assertions.assertThat(hazelcastMapCacheManager.clearReplicatedMap(CACHE_NAME).message)
            .isEqualTo("Oops! Something went wrong during clearing ReplicatedMap[cacheName]... Oops")
        verify(hazelcastInstance).getReplicatedMap<Any, Any>(CACHE_NAME)
    }

    @Test
    fun clearReplicatedMapForKey_clearsCacheForKey() {
        Assertions.assertThat(hazelcastMapCacheManager.clearReplicatedMapForKey("key", CACHE_NAME).message).isEqualTo("ReplicatedMap[cacheName] for key=key cleared successfully")
        verify(hazelcastInstance).getReplicatedMap<Any, Any>(CACHE_NAME)
        verify(replicatedMap).remove("key")
    }

    @Test
    fun clearDistributedMapForKey_clearsCacheForKey() {
        Assertions.assertThat(hazelcastMapCacheManager.clearDistributedMapForKey("key", CACHE_NAME).message).isEqualTo("IMap[cacheName] for key=key cleared successfully")
        verify(hazelcastInstance).getMap<Any, Any>(CACHE_NAME)
        verify(distributedMap).evict("key")
    }

    @Test
    fun clearReplicatedMapForKey_whenHazelcastException_returnsErrorMessage() {
        `when`(hazelcastInstance.getReplicatedMap<Any, Any>(CACHE_NAME)).thenThrow(RuntimeException("Oops"))

        Assertions.assertThat(hazelcastMapCacheManager.clearReplicatedMapForKey("key", CACHE_NAME).message).isEqualTo("Oops! Something went wrong during clearing ReplicatedMap[cacheName]... Oops")
        verify(hazelcastInstance).getReplicatedMap<Any, Any>(CACHE_NAME)
    }

    @Test
    fun clearDistributedMapForKey_whenHazelcastException_returnsErrorMessage() {
        `when`(hazelcastInstance.getMap<Any, Any>(CACHE_NAME)).thenThrow(RuntimeException("Oops"))

        Assertions.assertThat(hazelcastMapCacheManager.clearDistributedMapForKey("key", CACHE_NAME).message).isEqualTo("Oops! Something went wrong during clearing IMap[cacheName]... Oops")
        verify(hazelcastInstance).getMap<Any, Any>(CACHE_NAME)
    }
}
