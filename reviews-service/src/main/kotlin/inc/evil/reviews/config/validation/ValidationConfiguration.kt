package inc.evil.reviews.config.validation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.validation.Validation

@Configuration
class ValidationConfiguration {
    @Bean
    fun validator(): javax.validation.Validator? {
        return Validation.buildDefaultValidatorFactory().validator
    }
}
