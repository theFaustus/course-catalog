package inc.evil.reviews.common.fixtures

import inc.evil.reviews.web.dto.ReviewResponse

class ReviewResponseFixture {
    companion object {
        fun of(
            id: Int? = -1,
            text: String = "Amazing, loved it!",
            author: String = "Anonymous",
            createdAt: String? = "2022-11-14T00:08:54.266024",
            lastModifiedAt: String? = "2022-11-14T00:08:54.266024",
            courseId: Int = 3
        ) = ReviewResponse(id, text, author, createdAt, lastModifiedAt, courseId)
    }
}
