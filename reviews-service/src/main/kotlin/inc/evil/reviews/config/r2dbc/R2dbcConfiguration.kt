package inc.evil.reviews.config.r2dbc

import inc.evil.reviews.repo.ReviewRepository
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.DialectResolver
import org.springframework.data.r2dbc.dialect.R2dbcDialect
import org.springframework.data.r2dbc.query.UpdateMapper
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.data.relational.core.dialect.RenderContextFactory
import org.springframework.data.relational.core.sql.render.SqlRenderer
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.time.*
import java.util.*

@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories
@EnableTransactionManagement
class R2dbcConfiguration {

    @Bean
    fun r2dbcCustomConversions(connectionFactory: ConnectionFactory): R2dbcCustomConversions? {
        val converters: MutableList<Any?> = ArrayList()
        converters.add(InstantWriteConverter.INSTANCE)
        converters.add(InstantReadConverter.INSTANCE)
        converters.add(BitSetReadConverter.INSTANCE)
        converters.add(DurationWriteConverter.INSTANCE)
        converters.add(DurationReadConverter.INSTANCE)
        converters.add(ZonedDateTimeReadConverter.INSTANCE)
        converters.add(ZonedDateTimeWriteConverter.INSTANCE)
        converters.add(ReviewRepository.ReviewsCountPerAuthorViewReadConverter())
        return R2dbcCustomConversions.of(DialectResolver.getDialect(connectionFactory), converters)
    }

    @Bean
    fun dialect(connectionFactory: ConnectionFactory?): R2dbcDialect? {
        return DialectResolver.getDialect(connectionFactory!!)
    }

    @Bean
    fun updateMapper(dialect: R2dbcDialect?, mappingR2dbcConverter: MappingR2dbcConverter?): UpdateMapper? {
        return UpdateMapper(dialect!!, mappingR2dbcConverter!!)
    }

    @Bean
    fun sqlRenderer(dialect: R2dbcDialect?): SqlRenderer? {
        val factory = RenderContextFactory(dialect!!)
        return SqlRenderer.create(factory.createRenderContext())
    }

    @WritingConverter
    enum class InstantWriteConverter : Converter<Instant, LocalDateTime> {
        INSTANCE;

        override fun convert(source: Instant): LocalDateTime {
            return LocalDateTime.ofInstant(source, ZoneOffset.UTC)
        }
    }

    @ReadingConverter
    enum class InstantReadConverter : Converter<LocalDateTime, Instant> {
        INSTANCE;

        override fun convert(localDateTime: LocalDateTime): Instant {
            return localDateTime.toInstant(ZoneOffset.UTC)
        }
    }

    @ReadingConverter
    enum class BitSetReadConverter : Converter<BitSet, Boolean> {
        INSTANCE;

        override fun convert(bitSet: BitSet): Boolean {
            return bitSet[0]
        }
    }

    @ReadingConverter
    enum class ZonedDateTimeReadConverter : Converter<LocalDateTime, ZonedDateTime> {
        INSTANCE;

        override fun convert(localDateTime: LocalDateTime): ZonedDateTime {
            // Be aware - we are using the UTC timezone
            return ZonedDateTime.of(localDateTime, ZoneOffset.UTC)
        }
    }

    @WritingConverter
    enum class ZonedDateTimeWriteConverter : Converter<ZonedDateTime, LocalDateTime> {
        INSTANCE;

        override fun convert(zonedDateTime: ZonedDateTime): LocalDateTime {
            return zonedDateTime.toLocalDateTime()
        }
    }

    @WritingConverter
    enum class DurationWriteConverter : Converter<Duration, Long> {
        INSTANCE;

        override fun convert(source: Duration): Long {
            return source.toMillis()
        }
    }

    @ReadingConverter
    enum class DurationReadConverter : Converter<Long, Duration> {
        INSTANCE;

        override fun convert(source: Long): Duration? {
            return if (source != null) Duration.ofMillis(source) else null
        }
    }
}
