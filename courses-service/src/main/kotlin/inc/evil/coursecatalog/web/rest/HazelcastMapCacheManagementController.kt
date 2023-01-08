package inc.evil.coursecatalog.web.rest

import inc.evil.coursecatalog.service.HazelcastMapCacheManager
import inc.evil.coursecatalog.web.dto.OperationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/hazelcast-cache")
class HazelcastMapCacheManagementController(val hazelcastMapCacheManager: HazelcastMapCacheManager) {
    @DeleteMapping("/distributed/{cacheName}/{key}")
    fun clearDistributedMapForKey(@PathVariable cacheName: String, @PathVariable key: String): ResponseEntity<OperationResponse> {
        return ResponseEntity.ok(hazelcastMapCacheManager.clearDistributedMapForKey(key, cacheName))
    }
    @DeleteMapping("/distributed/{cacheName}")
    fun clearDistributedMap(@PathVariable cacheName: String): ResponseEntity<OperationResponse> {
        return ResponseEntity.ok(hazelcastMapCacheManager.clearDistributedMap(cacheName))
    }
    @GetMapping("/distributed/{cacheName}")
    fun getDistributedMap(@PathVariable cacheName: String): ResponseEntity<Map<Any, Any>> {
        return ResponseEntity.ok(hazelcastMapCacheManager.getDistributedMap(cacheName))
    }
    @DeleteMapping("/replicated/{cacheName}/{key}")
    fun clearReplicatedMapForKey(@PathVariable cacheName: String, @PathVariable key: String): ResponseEntity<OperationResponse> {
        return ResponseEntity.ok(hazelcastMapCacheManager.clearReplicatedMapForKey(key, cacheName))
    }
    @DeleteMapping("/replicated/{cacheName}")
    fun clearReplicatedMap(@PathVariable cacheName: String): ResponseEntity<OperationResponse> {
        return ResponseEntity.ok(hazelcastMapCacheManager.clearReplicatedMap(cacheName))
    }
    @GetMapping("/replicated/{cacheName}")
    fun getReplicatedMap(@PathVariable cacheName: String): ResponseEntity<Map<Any, Any>> {
        return ResponseEntity.ok(hazelcastMapCacheManager.getReplicatedMap(cacheName))
    }

}
