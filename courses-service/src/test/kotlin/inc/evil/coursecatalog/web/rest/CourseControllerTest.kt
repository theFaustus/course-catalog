package inc.evil.coursecatalog.web.rest

import inc.evil.coursecatalog.common.WebUnitTest
import inc.evil.coursecatalog.common.dto.ErrorResponse
import inc.evil.coursecatalog.common.fixtures.CourseResponseFixture
import inc.evil.coursecatalog.facade.CourseFacade
import inc.evil.coursecatalog.web.dto.CourseRequest
import inc.evil.coursecatalog.web.dto.CourseResponse
import inc.evil.coursecatalog.web.dto.InstructorRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebUnitTest
@WebMvcTest(controllers = [CourseController::class])
internal class CourseControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var courseFacade: CourseFacade

    private val expectedCourse = CourseResponseFixture.of()

    @Test
    fun getCourseById() {
        `when`(courseFacade.findById(-1)).thenReturn(expectedCourse)

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
        verify(courseFacade).findById(-1)
    }

    @Test
    fun getAllCourses() {
        `when`(courseFacade.findAll()).thenReturn(listOf(expectedCourse))

        val coursesResponse = webTestClient.get()
            .uri("/api/v1/courses")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBodyList(CourseResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(coursesResponse).hasSize(1).containsExactly(expectedCourse)
    }

    @Test
    fun deleteCourseById() {
        val id = -1

        webTestClient.delete()
            .uri("/api/v1/courses/{id}", id)
            .exchange()
            .expectStatus().is2xxSuccessful

        verify(courseFacade).deleteById(-1)
    }

    @Test
    fun createCourse() {
        val courseRequest = CourseRequest("Kotlin Development", "Java", "TUTORIAL", InstructorRequest("Bruce Eckel"))
        `when`(courseFacade.save(courseRequest)).thenReturn(expectedCourse)

        webTestClient.post()
            .uri("/api/v1/courses")
            .bodyValue(courseRequest)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().location("http://localhost/api/v1/courses/-1")
            .expectBody(CourseResponse::class.java)

        verify(courseFacade).save(courseRequest)
    }

    @Test
    fun createCourse_withNoCategory_returnsBadRequest() {
        val courseRequest = CourseRequest("Kotlin Development", "Java", "", InstructorRequest("Bruce Eckel"))
        `when`(courseFacade.save(courseRequest)).thenReturn(expectedCourse)

        val errorResponse = webTestClient.post()
            .uri("/api/v1/courses")
            .bodyValue(courseRequest)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(ErrorResponse::class.java)
            .returnResult().responseBody

        assertThat(errorResponse?.path).isEqualTo("")
        assertThat(errorResponse?.messages).hasSize(1)
        assertThat(errorResponse?.messages?.contains("Field 'category' must not be blank, but value was []")).isTrue

    }

}
