package inc.evil.reviews.repo

import inc.evil.reviews.model.*
import io.r2dbc.spi.Row
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface ReviewRepository : R2dbcRepository<Review, Int> {
    @Query("select * from reviews r where date(r.created_at) = :date")
    fun findAllByCreatedAt(date: LocalDate): Flux<Review>
    fun findAllByAuthor(author: String): Flux<Review>
    fun findAllByCreatedAtBetween(startDateTime: LocalDateTime, endDateTime: LocalDateTime): Flux<Review>

    @Query("select course_id, \"text\", author from reviews")
    fun findAllAuthoredTextProjections(): Flux<AuthoredTextProjection>

    @Query("select text from reviews where author = :author")
    fun findAllTextProjectionsByAuthor(author: String): Flux<TextProjection>

    @Query("select author, count(id) as \"numberOfReviews\" from reviews group by author")
    fun countReviewsPerAuthor(): Flux<ReviewsCountPerAuthorView>

    @ReadingConverter
    class ReviewsCountPerAuthorViewReadConverter : Converter<Row, ReviewsCountPerAuthorView> {
        override fun convert(source: Row): ReviewsCountPerAuthorView {
            return ReviewsCountPerAuthorView(source.get("author", String::class.java), source.get("numberOfReviews", String::class.java)?.toInt())
        }
    }

}
