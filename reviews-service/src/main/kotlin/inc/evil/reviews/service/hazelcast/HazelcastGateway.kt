package inc.evil.reviews.service.hazelcast

import com.hazelcast.core.HazelcastInstance
import org.springframework.stereotype.Component
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

@Component
class HazelcastGateway(
    private val hazelcastInstance: HazelcastInstance
) {

    companion object {
        private const val EXECUTOR_SERVICE_NAME = "EXECUTOR_SERVICE"
    }

    fun <R> execute(executionRequest: Callable<R>): R {
        val ex = hazelcastInstance.getExecutorService(EXECUTOR_SERVICE_NAME)
        return ex.submit(executionRequest).get(15000L, TimeUnit.MILLISECONDS)
//        return ex.executeOnAllMembers(executionRequest).get(15000L, TimeUnit.MILLISECONDS)
//        return ex.submitToAllMembers(executionRequest).get(15000L, TimeUnit.MILLISECONDS)
//        return ex.submitToMember(executionRequest, hazelcastInstance.cluster.members.iterator().next()).get(15000L, TimeUnit.MILLISECONDS)
//        return ex.submitToKeyOwner(executionRequest, "key").get(15000L, TimeUnit.MILLISECONDS)
    }
}
