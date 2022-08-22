package inc.evil.coursecatalog.web

import inc.evil.coursecatalog.common.IntegrationTest
import inc.evil.coursecatalog.repo.CourseRepository
import inc.evil.coursecatalog.web.dto.CourseRequest
import inc.evil.coursecatalog.web.dto.CourseResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient

@IntegrationTest
internal class CourseControllerIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @AfterEach
    internal fun tearDown() {
        courseRepository.deleteAll()
    }

    @Test
    @Sql(scripts = ["/test-data/courses.sql"])
    fun getCourseById() {
        val expectedCourse = CourseResponse(
            -1,
            "Kotlin course",
            "DEVELOPMENT",
            "2022-08-22 20:22:36.510984",
            "2022-08-22 20:22:36.572486",
        )

        val courseResponse = webTestClient.get()
            .uri("/api/v1/courses/{id}", expectedCourse.id)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(CourseResponse::class.java)
            .returnResult()
            .responseBody


        courseResponse?.let {
            assertThat(it.id).isEqualTo(expectedCourse.id)
            assertThat(it.name).isEqualTo(expectedCourse.name)
            assertThat(it.category).isEqualTo(expectedCourse.category)
        }
    }

    @Test
    @Sql(scripts = ["/test-data/courses.sql"])
    fun getAllCourses() {
        val coursesResponse = webTestClient.get()
            .uri("/api/v1/courses")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBodyList(CourseResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(coursesResponse).hasSize(2)
    }

    @Test
    @Sql(scripts = ["/test-data/courses.sql"])
    fun deleteCourseById() {
        val id = -1

        webTestClient.delete()
            .uri("/api/v1/courses/{id}", id)
            .exchange()
            .expectStatus().is2xxSuccessful

        assertThat(courseRepository.findById(-1)).isEmpty
    }

    @Test
    fun createCourse() {
        val courseResponse = webTestClient.post()
            .uri("/api/v1/courses")
            .bodyValue(CourseRequest("Kotlin Development", "DEVELOPMENT"))
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(courseResponse?.id).isNotNull
    }

}
