package inc.evil.reviews.repo

import inc.evil.reviews.model.Review
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : R2dbcRepository<Review, Int> {
}
