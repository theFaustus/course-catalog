package inc.evil.reviews.facade.impl

import inc.evil.reviews.common.fixtures.ReviewFixture
import inc.evil.reviews.common.fixtures.ReviewResponseFixture
import inc.evil.reviews.facade.ReviewFacade
import inc.evil.reviews.service.ReviewService
import inc.evil.reviews.web.dto.ReviewRequest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
internal class ReviewFacadeImplTest {

    private var reviewService: ReviewService = mock(ReviewService::class.java)
    private var reviewFacade: ReviewFacade = ReviewFacadeImpl(reviewService)

    @Test
    fun findAll() {
        reviewService.stub {
            onBlocking { reviewService.findAll() }.thenReturn(listOf(ReviewFixture.of()))
        }
        runBlocking {
            Assertions.assertThat(reviewFacade.findAll()).contains(
                ReviewResponseFixture.of(
                    text = "What a nice course",
                    author = "Squidward",
                    createdAt = "1970-01-01T00:16:40",
                    lastModifiedAt = "1970-01-01T00:16:40"
                )
            )
            verify(reviewService).findAll()
        }
    }

    @Test
    fun findById() {
        val id = -1
        reviewService.stub {
            onBlocking { reviewService.findById(id) }.thenReturn(ReviewFixture.of())
        }
        runBlocking {
            Assertions.assertThat(reviewFacade.findById(id)).isEqualTo(
                ReviewResponseFixture.of(
                    text = "What a nice course",
                    author = "Squidward",
                    createdAt = "1970-01-01T00:16:40",
                    lastModifiedAt = "1970-01-01T00:16:40"
                )
            )
            verify(reviewService).findById(id)
        }
    }

    @Test
    fun save() {
        val review = ReviewFixture.of(id = null, createdAt = null, lastModifiedAt = null)
        reviewService.stub {
            onBlocking { reviewService.save(review) }.thenReturn(review)
        }
        runBlocking {
            Assertions.assertThat(reviewFacade.save(ReviewRequest("What a nice course", "Squidward", courseId = 3)))
                .usingRecursiveComparison()
                .comparingOnlyFields()
                .isEqualTo(
                    ReviewResponseFixture.of(
                        id = null,
                        text = "What a nice course",
                        author = "Squidward",
                        createdAt = "null",
                        lastModifiedAt = "null"
                    )
                )
            verify(reviewService).save(review)
        }
    }

    @Test
    fun deleteById() {
        val id = -1

        runBlocking {
            reviewFacade.deleteById(id)
            verify(reviewService).deleteById(id)
        }
    }
}
