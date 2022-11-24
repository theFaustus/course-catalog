package inc.evil.reviews.service.impl

import inc.evil.reviews.common.fixtures.ReviewFixture
import inc.evil.reviews.model.Review
import inc.evil.reviews.service.ReviewService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class PdfReportGenerationServicePresentationMaterialTest {

    @Test
    fun generateReportFor_withDateNull_throwsIllegalArgumentException() {
        val reportGenerationService: PdfReportGenerationService = spy(PdfReportGenerationService(DummyReviewService()))
        Assertions.assertThatCode {
            runBlocking {
                reportGenerationService.generateReportFor(null)
            }
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("Date should not be null")
    }

    @Test
    fun generateReportById_withNonExistentId_returnsDefaultReview() {
        val reportGenerationService: PdfReportGenerationService = spy(PdfReportGenerationService(ReviewServiceStub()))
        Assertions.assertThatCode {
            runBlocking {
                reportGenerationService.generateReportById(1)
            }
        }.doesNotThrowAnyException()
    }

    @Test
    fun generateReportById_withExistingId_callsService() {
        val reviewService = ReviewServiceSpy()
        val reportGenerationService: PdfReportGenerationService = spy(PdfReportGenerationService(reviewService))
        Assertions.assertThatCode {
            runBlocking {
                reportGenerationService.generateReportById(1)
            }
        }.doesNotThrowAnyException()
        Assertions.assertThat(reviewService.numberOfInvocations).isEqualTo(1)
    }

    @Test
    fun generateReportById_withExistingId_worksWithFetchedReview() {
        val reviewService = ReviewServiceMock(expectedId = 1, expectedInvocations = 1)
        val reportGenerationService: PdfReportGenerationService = spy(PdfReportGenerationService(reviewService))
        Assertions.assertThatCode {
            runBlocking {
                reportGenerationService.generateReportById(1)
            }
        }.doesNotThrowAnyException()
        reviewService.verify()
    }

    class ReviewServiceMock(val expectedId: Int, val expectedInvocations: Int) : BaseReviewService() {
        var idInvocationsMap: HashMap<Int, Int> = HashMap()

        override suspend fun findById(id: Int): Review {
            idInvocationsMap.merge(id, 1, Integer::sum)
            return ReviewFixture.of()
        }

        fun verify() = Assertions.assertThat(idInvocationsMap).containsEntry(expectedId, expectedInvocations)
    }

    class ReviewServiceSpy : BaseReviewService() {
        var numberOfInvocations = 0

        override suspend fun findById(id: Int): Review {
            numberOfInvocations++
            return ReviewFixture.of()
        }
    }

    class ReviewServiceStub : BaseReviewService() {
        override suspend fun findById(id: Int): Review {
            throw inc.evil.reviews.common.exceptions.NotFoundException(Review::class, "id", id.toString())
        }
    }

    class DummyReviewService : BaseReviewService() {
        override suspend fun findAllByCreatedAt(date: LocalDate): List<Review> {
            return emptyList()
        }
    }

    open class BaseReviewService : ReviewService {
        override suspend fun findAll(): List<Review> {
            TODO("Not yet implemented")
        }

        override suspend fun save(review: Review): Review {
            TODO("Not yet implemented")
        }

        override suspend fun findById(id: Int): Review {
            TODO("Not yet implemented")
        }

        override suspend fun deleteById(id: Int): Void? {
            TODO("Not yet implemented")
        }

        override suspend fun findAllByCreatedAt(date: LocalDate): List<Review> {
            TODO("Not yet implemented")
        }

    }


}
