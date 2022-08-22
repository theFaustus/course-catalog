package inc.evil.coursecatalog.web

import inc.evil.coursecatalog.common.dto.SuccessResponse
import inc.evil.coursecatalog.service.GreetingsService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@AutoConfigureWebTestClient
@WebMvcTest(controllers = [GreetingController::class])
internal class GreetingControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var greetingsService: GreetingsService

    @Test
    fun retrieveGreeting() {
        val name = "Mike"
        `when`(greetingsService.retrieveGreeting(name)).thenReturn("Goooooood morning, Vieeeeetnaaam!")
        val result = webTestClient.get()
            .uri("/api/v1/greetings/{name}", name)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(SuccessResponse::class.java)
            .returnResult()

        Assertions.assertThat(result.responseBody?.response).isNotNull.isEqualTo("Goooooood morning, Vieeeeetnaaam!")
    }
}
