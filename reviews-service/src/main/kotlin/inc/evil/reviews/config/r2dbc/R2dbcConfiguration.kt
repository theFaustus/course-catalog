package inc.evil.reviews.config.r2dbc

import inc.evil.reviews.repo.ReviewRepository
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.DialectResolver
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories
@EnableTransactionManagement
class R2dbcConfiguration {
    @Bean
    fun r2dbcCustomConversions(connectionFactory: ConnectionFactory): R2dbcCustomConversions? {
        return R2dbcCustomConversions.of(
            DialectResolver.getDialect(connectionFactory),
            listOf(
                ReviewRepository.ReviewsCountPerAuthorViewReadConverter()
            )
        )
    }
}
