package inc.evil.reviews.common.fixtures

import inc.evil.reviews.model.Review
import java.time.LocalDateTime
import java.time.ZoneOffset

class ReviewFixture {
    companion object {
        fun of(
            id: Int? = -1,
            courseId: Int = 3,
            text: String = "What a nice course",
            author: String = "Squidward",
            createdAt: LocalDateTime? = LocalDateTime.ofEpochSecond(1000, 0, ZoneOffset.UTC),
            lastModifiedAt: LocalDateTime? = LocalDateTime.ofEpochSecond(1000, 0, ZoneOffset.UTC)
        ) =
            Review(id, text, author, createdAt, lastModifiedAt, courseId)

        fun michaelScottReview() = Review(-1, "well, well how the turn tables", "Michael Scott", LocalDateTime.now(), LocalDateTime.now(), 3)


    }
}
