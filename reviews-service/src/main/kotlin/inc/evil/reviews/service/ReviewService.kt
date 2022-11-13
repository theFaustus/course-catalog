package inc.evil.reviews.service

import inc.evil.reviews.model.Review

interface ReviewService {
    suspend fun findAll(): List<Review>
    suspend fun save(review: Review): Review
    suspend fun findById(id: Int): Review
    suspend fun deleteById(id: Int): Void?
}
