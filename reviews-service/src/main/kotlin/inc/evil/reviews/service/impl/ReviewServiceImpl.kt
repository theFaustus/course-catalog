package inc.evil.reviews.service.impl

import inc.evil.reviews.common.exceptions.NotFoundException
import inc.evil.reviews.model.Review
import inc.evil.reviews.repo.ReviewRepository
import inc.evil.reviews.service.ReviewService
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class ReviewServiceImpl(val reviewRepository: ReviewRepository) : ReviewService {
    override suspend fun findAll(): List<Review> {
        return reviewRepository.findAll().collectList().awaitFirst()
    }

    override suspend fun findById(id: Int): Review {
        return reviewRepository.findById(id).awaitFirstOrNull() ?: throw NotFoundException(Review::class, "id", id.toString())
    }

    override suspend fun save(review: Review): Review {
        return reviewRepository.save(review).awaitFirst()
    }

    override suspend fun deleteById(id: Int): Void? {
        return reviewRepository.deleteById(id).awaitFirstOrNull()
    }

    override suspend fun findAllByCreatedAt(date: LocalDate): List<Review> {
        return reviewRepository.findAllByCreatedAt(date).collectList().awaitFirst()
    }
}
