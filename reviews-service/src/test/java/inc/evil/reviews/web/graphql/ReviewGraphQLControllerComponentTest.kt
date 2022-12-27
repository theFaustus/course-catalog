package inc.evil.reviews.web.graphql

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IExecutorService
import inc.evil.courses.api.web.dto.CourseApiResponse
import inc.evil.courses.api.web.dto.InstructorApiResponse
import inc.evil.reviews.common.AbstractTestcontainersIntegrationTest
import inc.evil.reviews.common.ComponentTest
import inc.evil.reviews.common.fixtures.ReviewResponseFixture
import inc.evil.reviews.web.dto.ReviewResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.graphql.test.tester.GraphQlTester
import ro.orange.eshop.userordermanagement.common.RunSql
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

@ComponentTest
internal class ReviewGraphQLControllerComponentTest : AbstractTestcontainersIntegrationTest() {

    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockBean
    private lateinit var hazelcastInstance: HazelcastInstance

    @Test
    @RunSql(["schema.sql", "/data/reviews.sql"])
    fun getReviewById() {
        graphQlTester.documentName("getReviewById")
            .variable("id", -1)
            .execute()
            .path("getReviewById")
            .entity(ReviewResponse::class.java)
            .isEqualTo(ReviewResponseFixture.of())
    }

    @Test
    @RunSql(["schema.sql", "/data/reviews.sql"])
    fun getAllReviews() {
        graphQlTester.documentName("getAllReviews")
            .execute()
            .path("getAllReviews")
            .entityList(ReviewResponse::class.java)
            .hasSize(4)
            .contains(ReviewResponseFixture.of())
    }

    @Test
    @RunSql(["schema.sql", "/data/reviews.sql"])
    fun deleteReviewById() {
        graphQlTester.documentName("deleteReviewById")
            .variable("id", -1)
            .executeAndVerify()
        graphQlTester.documentName("getReviewById")
            .variable("id", -1)
            .execute()
            .errors()
            .expect { it.message == "Review with id equal to [-1] could not be found!" }
    }

    @Test
    @RunSql(["schema.sql", "/data/reviews.sql"])
    fun createReview() {
        val instructor = InstructorApiResponse(1, "name", "summary", "description")
        val courseApiResponse = CourseApiResponse(1, "name", "category", "java", "desc", "date", "date", instructor)

        val service = mock(IExecutorService::class.java)
        val future = mock(Future::class.java)
        whenever(future.get(15000L, TimeUnit.MILLISECONDS)).thenReturn(courseApiResponse)
        whenever(service.submit(any(Callable::class.java))).thenReturn(future)
        whenever(hazelcastInstance.getExecutorService("EXECUTOR_SERVICE")).thenReturn(service)

        graphQlTester.documentName("createReview")
            .variable("request", mapOf("author" to "Squidward", "text" to "What a nice course", "courseId" to 4))
            .execute()
            .path("createReview")
            .entity(ReviewResponse::class.java)
            .matches { it.text == "What a nice course" && it.author == "Squidward" && it.courseId == 4 }

    }

}
