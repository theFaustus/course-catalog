package inc.evil.reviews.facade.impl

import inc.evil.reviews.facade.ReviewFacade
import inc.evil.reviews.model.Review
import inc.evil.reviews.service.ReviewService
import inc.evil.reviews.web.dto.ReviewRequest
import inc.evil.reviews.web.dto.ReviewResponse
import org.springframework.stereotype.Component

@Component
class ReviewFacadeImpl(val reviewService: ReviewService) : ReviewFacade {
    override suspend fun findAll(): List<ReviewResponse> {
        return reviewService.findAll().map { ReviewResponse.from(it) }
    }

    override suspend fun findById(id: Int): ReviewResponse {
        return ReviewResponse.from(reviewService.findById(id))
    }

    override suspend fun save(reviewRequest: ReviewRequest): ReviewResponse {
        return ReviewResponse.from(reviewService.save(Review(text = reviewRequest.text, author = reviewRequest.author, courseId = reviewRequest.courseId)))
    }

    override suspend fun deleteById(id: Int) {
        reviewService.deleteById(id)
    }
}
