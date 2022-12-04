package inc.evil.reviews.repo

import inc.evil.reviews.common.AbstractTestcontainersIntegrationTest
import inc.evil.reviews.model.AuthoredTextProjection
import inc.evil.reviews.model.ReviewsCountPerAuthorView
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.test.StepVerifier
import ro.orange.eshop.userordermanagement.common.RepositoryIntegrationTest
import ro.orange.eshop.userordermanagement.common.RunSql
import java.time.LocalDate
import java.time.LocalDateTime

@RepositoryIntegrationTest
class ReviewRepositoryIntegrationTest : AbstractTestcontainersIntegrationTest() {

    @Autowired
    lateinit var reviewRepository: ReviewRepository

    @Test
    @RunSql(["/data/reviews.sql"])
    fun findAllByAuthor() {
        StepVerifier.create(reviewRepository.findAllByAuthor("Anonymous"))
            .expectNextCount(3)
            .verifyComplete()
    }

    @Test
    @RunSql(["/data/reviews.sql"])
    fun findAllByCreatedAt() {
        StepVerifier.create(reviewRepository.findAllByCreatedAt(LocalDate.parse("2022-11-14")))
            .expectNextCount(1)
            .verifyComplete()
    }

    @Test
    @RunSql(["/data/reviews.sql"])
    fun findAllByCreatedAtBetween() {
        StepVerifier.create(
            reviewRepository.findAllByCreatedAtBetween(
                LocalDateTime.parse("2022-11-14T00:08:54.266024"),
                LocalDateTime.parse("2022-11-17T00:08:56.902252")
            )
        )
            .expectNextCount(4)
            .verifyComplete()
    }

    @Test
    @RunSql(["/data/reviews.sql"])
    fun findAllAuthoredTextProjections() {
        StepVerifier.create(reviewRepository.findAllAuthoredTextProjections())
            .expectNextMatches { it.getAuthoredText() == "Amazing, loved it! - Anonymous" && it.getCourseId() == 3 }
            .expectNextMatches { it.getAuthoredText() == "Great, loved it! - Anonymous" && it.getCourseId() == 3 }
            .expectNextMatches { it.getAuthoredText() == "Good, loved it! - Sponge Bob" && it.getCourseId() == 3 }
            .expectNextMatches { it.getAuthoredText() == "Nice, loved it! - Anonymous" && it.getCourseId() == 3 }
            .verifyComplete()
    }

    @Test
    @RunSql(["/data/reviews.sql"])
    fun findAllTextProjectionsByAuthor() {
        StepVerifier.create(reviewRepository.findAllTextProjectionsByAuthor("Sponge Bob"))
            .expectNextMatches { it.text == "Good, loved it!" }
            .verifyComplete()
    }

    @Test
    @RunSql(["/data/reviews.sql"])
    fun countReviewsPerAuthor() {
        StepVerifier.create(reviewRepository.countReviewsPerAuthor())
            .expectNextMatches { it == ReviewsCountPerAuthorView("Anonymous", 3) }
            .expectNextMatches { it == ReviewsCountPerAuthorView("Sponge Bob", 1) }
            .verifyComplete()
    }

    class AuthoredTextProjectionImpl(val courseId: Int, val text: String) : AuthoredTextProjection {
        override fun getCourseId(): Int = courseId
        override fun getAuthoredText(): String = text

    }
}
