package inc.evil.coursecatalog.web.rest

import com.github.tomakehurst.wiremock.WireMockServer
import inc.evil.coursecatalog.common.*
import inc.evil.coursecatalog.common.dto.ErrorResponse
import inc.evil.coursecatalog.common.fixtures.InstructorResponseFixture
import inc.evil.coursecatalog.web.dto.InstructorRequest
import inc.evil.coursecatalog.web.dto.InstructorResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient

@TestcontainersIntegrationTest
@ContextConfiguration(initializers = [WireMockContextInitializer::class])
internal class InstructorControllerTestcontainersIntegrationTest : AbstractTestcontainersIntegrationTest() {

    @Autowired
    lateinit var webTestClient: WebTestClient
    @Autowired
    private lateinit var wireMockServer: WireMockServer


    @Test
    @Sql(scripts = ["/postgres/courses.sql"])
    fun getInstructorById() {
        val expectedInstructor = InstructorResponseFixture.of()

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
    }

    @Test
    @Sql(scripts = ["/postgres/courses.sql"])
    fun getAllInstructors() {
        val instructorsResponse = webTestClient.get()
            .uri("/api/v1/instructors")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBodyList(InstructorResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(instructorsResponse).hasSize(1)
    }

    @Test
    @Sql(scripts = ["/postgres/courses.sql"])
    fun deleteInstructorById() {
        val id = -1

        webTestClient.delete()
            .uri("/api/v1/instructors/{id}", id)
            .exchange()
            .expectStatus().is2xxSuccessful

        val instructorResponse = webTestClient.get()
            .uri("/api/v1/instructors/{id}", id)
            .exchange()
            .expectStatus().isNotFound
            .expectBody(ErrorResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(instructorResponse?.messages).isEqualTo(setOf("Instructor with id equal to [-1] could not be found!"))
    }

    @Test
    fun createInstructor() {
        val responseBody = IO.read("/json/wikipediea-response.json")
        wireMockServer.stubResponse("/page/summary/Bruce%20Eckel", responseBody)

        val instructorResponse = webTestClient.post()
            .uri("/api/v1/instructors")
            .bodyValue(InstructorRequest("Bruce Eckel"))
            .exchange()
            .expectStatus().isCreated
            .expectBody(InstructorResponse::class.java)
            .returnResult()
            .responseBody

        assertThat(instructorResponse?.id).isNotNull
    }

}
