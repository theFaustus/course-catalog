package inc.evil.reviews.web.dto

import inc.evil.reviews.model.Review

data class ReviewResponse(
    val id: Int?,
    val text: String,
    val author: String,
    val createdAt: String?,
    val lastModifiedAt: String?,
    val courseId: Int?,
) {
    companion object {
        fun from(review: Review) = ReviewResponse(review.id, review.text, review.author, review.createdAt.toString(), review.lastModifiedAt.toString(), review.courseId)
    }
}
