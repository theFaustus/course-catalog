package inc.evil.coursecatalog.web.rest

import inc.evil.coursecatalog.common.WebUnitTest
import inc.evil.coursecatalog.common.dto.ErrorResponse
import inc.evil.coursecatalog.facade.InstructorFacade
import inc.evil.coursecatalog.web.dto.InstructorRequest
import inc.evil.coursecatalog.web.dto.InstructorResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebUnitTest
@WebMvcTest(controllers = [InstructorController::class])
internal class InstructorControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var instructorFacade: InstructorFacade

    private val expectedInstructor = instructorResponse()

    @Test
    fun getInstructorById() {
        `when`(instructorFacade.findById(-1)).thenReturn(expectedInstructor)

        val instructorResponse = webTestClient.get()
            .uri("/api/v1/instructors/{id}", expectedInstructor.id)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(InstructorResponse::class.java)
            .returnResult()
            .responseBody

        instructorResponse?.let {
            assertThat(it.id).isEqualTo(expectedInstructor.id)
            assertThat(it.name).isEqualTo(expectedInstructor.name)
        }
        verify(instructorFacade).findById(-1)
    }

    @Test
    fun getAllInstructors() {
        `when`(instructorFacade.findAll()).thenReturn(listOf(expectedInstructor))

        val instructorsResponse = webTestClient.get()
            .uri("/api/v1/instructors")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBodyList(InstructorResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(instructorsResponse).hasSize(1).containsExactly(expectedInstructor)
    }

    @Test
    fun deleteInstructorById() {
        val id = -1

        webTestClient.delete()
            .uri("/api/v1/instructors/{id}", id)
            .exchange()
            .expectStatus().is2xxSuccessful

        verify(instructorFacade).deleteById(-1)
    }

    @Test
    fun createInstructor() {
        val instructorRequest = InstructorRequest("Bruce Eckel")
        `when`(instructorFacade.save(instructorRequest)).thenReturn(expectedInstructor)

        webTestClient.post()
            .uri("/api/v1/instructors")
            .bodyValue(instructorRequest)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().location("http://localhost/api/v1/instructors/-1")
            .expectBody(InstructorResponse::class.java)

        verify(instructorFacade).save(instructorRequest)
    }

    @Test
    fun createInstructor_withNoCategory_returnsBadRequest() {
        val instructorRequest = InstructorRequest("")
        `when`(instructorFacade.save(instructorRequest)).thenReturn(expectedInstructor)

        val errorResponse = webTestClient.post()
            .uri("/api/v1/instructors")
            .bodyValue(instructorRequest)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(ErrorResponse::class.java)
            .returnResult().responseBody

        assertThat(errorResponse?.path).isEqualTo("")
        assertThat(errorResponse?.messages).hasSize(1)
        assertThat(errorResponse?.messages?.contains("Field 'name' must not be blank, but value was []")).isTrue

    }

    private fun instructorResponse() = InstructorResponse(-1, "Bruce Eckel")
}
