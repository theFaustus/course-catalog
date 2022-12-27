package inc.evil.coursecatalog.config.hazelcast

import com.hazelcast.config.Config
import com.hazelcast.config.EvictionPolicy
import com.hazelcast.config.MapConfig
import com.hazelcast.config.MaxSizePolicy
import com.hazelcast.spring.context.SpringManagedContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HazelcastConfiguration {

    companion object {
        const val WIKIPEDIA_SUMMARIES = "WIKIPEDIA_SUMMARIES"
    }

    @Bean
    fun managedContext(): SpringManagedContext {
        return SpringManagedContext()
    }

    @Bean
    fun hazelcastConfig(managedContext: SpringManagedContext): Config {
        val config = Config()
        config.managedContext = managedContext
        config.networkConfig.isPortAutoIncrement = true
        config.networkConfig.join.multicastConfig.isEnabled = true
        config.networkConfig.join.multicastConfig.multicastPort = 5777
        config.userCodeDeploymentConfig.isEnabled = true
        config.configureWikipediaSummaries()
        return config
    }


    private fun Config.configureWikipediaSummaries() {
        val wikipediaSummaries = MapConfig()
        wikipediaSummaries.name = WIKIPEDIA_SUMMARIES
        wikipediaSummaries.isStatisticsEnabled = true
        wikipediaSummaries.backupCount = 1
        wikipediaSummaries.evictionConfig.evictionPolicy = EvictionPolicy.LRU
        wikipediaSummaries.evictionConfig.size = 10000
        wikipediaSummaries.evictionConfig.maxSizePolicy = MaxSizePolicy.PER_NODE

        addMapConfig(wikipediaSummaries)
    }
}

