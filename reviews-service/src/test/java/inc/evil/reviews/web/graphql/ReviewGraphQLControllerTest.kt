package inc.evil.reviews.web.graphql

import inc.evil.reviews.common.AbstractTestcontainersIntegrationTest
import inc.evil.reviews.common.ComponentTest
import inc.evil.reviews.common.fixtures.ReviewResponseFixture
import inc.evil.reviews.web.dto.ReviewResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.test.tester.GraphQlTester
import ro.orange.eshop.userordermanagement.common.RunSql

@ComponentTest
internal class ReviewGraphQLControllerTest : AbstractTestcontainersIntegrationTest() {

    @Autowired
    private lateinit var graphQlTester: GraphQlTester

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
        graphQlTester.documentName("createReview")
            .variable("request", mapOf("author" to "Squidward", "text" to "What a nice course", "courseId" to 4))
            .execute()
            .path("createReview")
            .entity(ReviewResponse::class.java)
            .matches { it.text == "What a nice course" && it.author == "Squidward" && it.courseId == 4 }

    }

}
