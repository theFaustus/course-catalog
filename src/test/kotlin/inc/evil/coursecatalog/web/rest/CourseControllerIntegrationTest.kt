package inc.evil.coursecatalog.web.rest

import inc.evil.coursecatalog.common.IntegrationTest
import inc.evil.coursecatalog.common.dto.ErrorResponse
import inc.evil.coursecatalog.web.dto.CourseRequest
import inc.evil.coursecatalog.web.dto.CourseResponse
import inc.evil.coursecatalog.web.dto.InstructorRequest
import inc.evil.coursecatalog.web.dto.InstructorResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient

@IntegrationTest
internal class CourseControllerIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    @Sql(scripts = ["/h2/courses.sql"])
    fun getCourseById() {
        val expectedCourse = CourseResponse(
            -1,
            "Kotlin course",
            "DEVELOPMENT",
            "2022-08-22 20:22:36.510984",
            "2022-08-22 20:22:36.572486",
            InstructorResponse(-9, "Bruce Eckel")
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
    @Sql(scripts = ["/h2/courses.sql"])
    fun getAllCourses() {
        val coursesResponse = webTestClient.get()
            .uri("/api/v1/courses")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBodyList(CourseResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(coursesResponse).hasSize(3)
    }

    @Test
    @Sql(scripts = ["/h2/courses.sql"])
    fun deleteCourseById() {
        val id = -1

        webTestClient.delete()
            .uri("/api/v1/courses/{id}", id)
            .exchange()
            .expectStatus().is2xxSuccessful

        val courseResponse = webTestClient.get()
            .uri("/api/v1/courses/{id}", id)
            .exchange()
            .expectStatus().isNotFound
            .expectBody(ErrorResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(courseResponse?.messages).isEqualTo(setOf("Course with id equal to [-1] could not be found!"))    }

    @Test
    fun createCourse() {
        val courseResponse = webTestClient.post()
            .uri("/api/v1/courses")
            .bodyValue(CourseRequest("Kotlin Development", "DEVELOPMENT", InstructorRequest("Bruce Eckel")))
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(courseResponse?.id).isNotNull
    }

}
