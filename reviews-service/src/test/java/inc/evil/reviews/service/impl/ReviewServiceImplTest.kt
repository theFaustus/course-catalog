package inc.evil.reviews.service.impl

import inc.evil.reviews.common.fixtures.ReviewFixture
import inc.evil.reviews.repo.ReviewRepository
import inc.evil.reviews.service.ReviewService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@ExtendWith(MockitoExtension::class)
internal class ReviewServiceImplTest {

    private var reviewRepository: ReviewRepository = mock(ReviewRepository::class.java)
    private var reviewService: ReviewService = ReviewServiceImpl(reviewRepository)

    @Test
    fun findAll() {
        val review = ReviewFixture.of()
        `when`(reviewRepository.findAll()).thenReturn(Flux.just(review))

        runBlocking {
            Assertions.assertThat(reviewService.findAll()).contains(review)
            verify(reviewRepository).findAll()
        }
    }

    @Test
    fun findById() {
        val review = ReviewFixture.of()
        val id = review.id!!
        `when`(reviewRepository.findById(id)).thenReturn(Mono.just(review))

        runBlocking {
            Assertions.assertThat(reviewService.findById(id)).isEqualTo(review)
            verify(reviewRepository).findById(id)
        }
    }

    @Test
    fun save() {
        val review = ReviewFixture.of()
        `when`(reviewRepository.save(review)).thenReturn(Mono.just(review))

        runBlocking {
            Assertions.assertThat(reviewService.save(review)).isEqualTo(review)
            verify(reviewRepository).save(review)
        }
    }

    @Test
    fun deleteById() {
        val review = ReviewFixture.of()
        val id = review.id!!
        `when`(reviewRepository.deleteById(id)).thenReturn(Mono.empty())

        runBlocking {
            reviewService.deleteById(id)
            verify(reviewRepository).deleteById(id)
        }
    }
}
