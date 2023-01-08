package inc.evil.coursecatalog.service.impl

import com.hazelcast.core.HazelcastInstance
import inc.evil.coursecatalog.service.HazelcastMapCacheManager
import inc.evil.coursecatalog.web.dto.OperationResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

import java.util.*


@Component
class HazelcastMapCacheManagerImpl(@Qualifier("hazelcastInstance") val hazelcastInstance: HazelcastInstance) : HazelcastMapCacheManager {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun getDistributedMap(cacheName: String): Map<Any, Any> {
        return try {
            log.info("Retrieving IMap[$cacheName]")
            hazelcastInstance.getMap<Any, Any>(cacheName).toMap()
        } catch (e: Exception) {
            log.warn("Oops! Something went wrong during IMap[$cacheName] retrieval... ${e.message}")
            emptyMap()
        }
    }

    override fun getReplicatedMap(cacheName: String): Map<Any, Any> {
        return try {
            log.info("Retrieving ReplicatedMap[$cacheName]")
            hazelcastInstance.getReplicatedMap<Any, Any>(cacheName).toMap()
        } catch (e: Exception) {
            log.warn("Oops! Something went wrong during ReplicatedMap[$cacheName] retrieval... ${e.message}")
            emptyMap()
        }
    }

    override fun clearDistributedMapForKey(key: String, cacheName: String): OperationResponse {
        return try {
            hazelcastInstance.getMap<Any, Any>(cacheName).evict(key)
            OperationResponse("IMap[$cacheName] for key=$key cleared successfully")
        } catch (e: Exception) {
            OperationResponse("Oops! Something went wrong during clearing IMap[$cacheName]... ${e.message}")
        }
    }

    override fun clearDistributedMap(cacheName: String): OperationResponse {
        return try {
            hazelcastInstance.getMap<Any, Any>(cacheName).clear()
            OperationResponse("IMap[$cacheName] cleared successfully")
        } catch (e: Exception) {
            OperationResponse("Oops! Something went wrong during clearing IMap[$cacheName]... ${e.message}")
        }
    }

    override fun clearReplicatedMapForKey(key: String, cacheName: String): OperationResponse {
        return try {
            hazelcastInstance.getReplicatedMap<Any, Any>(cacheName).remove(key)
            OperationResponse("ReplicatedMap[$cacheName] for key=$key cleared successfully")
        } catch (e: Exception) {
            OperationResponse("Oops! Something went wrong during clearing ReplicatedMap[$cacheName]... ${e.message}")
        }
    }

    override fun clearReplicatedMap(cacheName: String): OperationResponse {
        return try {
            hazelcastInstance.getReplicatedMap<Any, Any>(cacheName).clear()
            OperationResponse("ReplicatedMap[$cacheName] cleared successfully")
        } catch (e: Exception) {
            OperationResponse("Oops! Something went wrong during clearing ReplicatedMap[$cacheName]... ${e.message}")
        }
    }

}
