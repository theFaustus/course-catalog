package inc.evil.reviews.config.aop

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment

@Configuration
@EnableAspectJAutoProxy
class AopConfiguration {
    @Bean
    @Profile("dev")
    fun loggingAspect(env: Environment): LoggingAspect? {
        return LoggingAspect(env)
    }
}
