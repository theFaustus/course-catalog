package inc.evil.reviews.service.impl

import inc.evil.courses.api.web.dto.CourseApiResponse
import inc.evil.courses.api.web.dto.InstructorApiResponse
import inc.evil.reviews.common.fixtures.ReviewFixture
import inc.evil.reviews.repo.ReviewRepository
import inc.evil.reviews.service.ReviewService
import inc.evil.reviews.service.hazelcast.GetCourseByIdCallable
import inc.evil.reviews.service.hazelcast.HazelcastGateway
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@ExtendWith(MockitoExtension::class)
internal class ReviewServiceImplTest {

    private var reviewRepository: ReviewRepository = mock(ReviewRepository::class.java)
    private var hazelcastGateway: HazelcastGateway = mock(HazelcastGateway::class.java)
    private var reviewService: ReviewService = ReviewServiceImpl(reviewRepository, hazelcastGateway)

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
        val instructor = InstructorApiResponse(1, "name", "summary", "description")
        val courseApiResponse = CourseApiResponse(1, "name", "category", "java", "desc", "date", "date", instructor)

        `when`(reviewRepository.save(review)).thenReturn(Mono.just(review))
        whenever(hazelcastGateway.execute(org.mockito.kotlin.any<GetCourseByIdCallable>())).thenReturn(courseApiResponse)

        runBlocking {
            Assertions.assertThat(reviewService.save(review)).isEqualTo(review)
            verify(reviewRepository).save(review)
        }
    }

    @Test
    fun deleteById_whenInvoked_callsRepository() {
        //Fixture setup - Given - Arrange
        val review = ReviewFixture.of()
        val id = review.id!!
        `when`(reviewRepository.deleteById(id)).thenReturn(Mono.empty())

        runBlocking {
            //Exercise system - When - Act
            reviewService.deleteById(id)
            //Verify outcome - Then - Assert
            verify(reviewRepository).deleteById(id)
        }

        //Teardown - Annihilate
        //Usually garbage collector does the magic
    }
}
