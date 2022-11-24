package inc.evil.reviews.repo

import inc.evil.reviews.model.Review
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface ReviewRepository : R2dbcRepository<Review, Int> {
    fun findAllByCreatedAt_Date(date: LocalDate): Flux<Review>
    fun findAllByAuthor(author: String): Flux<Review>
    fun findAllByCreatedAtBetween(startDateTime: LocalDateTime, endDateTime: LocalDateTime): Flux<Review>
}
