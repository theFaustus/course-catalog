package inc.evil.coursecatalog.service

import inc.evil.coursecatalog.web.dto.OperationResponse

interface HazelcastMapCacheManager {
    fun clearDistributedMapForKey(key: String, cacheName: String): OperationResponse
    fun clearDistributedMap(cacheName: String): OperationResponse
    fun clearReplicatedMapForKey(key: String, cacheName: String): OperationResponse
    fun clearReplicatedMap(cacheName: String): OperationResponse
    fun getDistributedMap(cacheName: String): Map<Any, Any>
    fun getReplicatedMap(cacheName: String): Map<Any, Any>
}
