package inc.evil.reviews.facade

import inc.evil.reviews.web.dto.ReviewRequest
import inc.evil.reviews.web.dto.ReviewResponse

interface ReviewFacade {
    suspend fun findAll(): List<ReviewResponse>
    suspend fun findById(id: Int): ReviewResponse
    suspend fun save(reviewRequest: ReviewRequest): ReviewResponse
    suspend fun deleteById(id: Int)
}
