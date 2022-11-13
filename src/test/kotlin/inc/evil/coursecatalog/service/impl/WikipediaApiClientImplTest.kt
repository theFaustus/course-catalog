package inc.evil.coursecatalog.service.impl

import com.github.tomakehurst.wiremock.WireMockServer
import inc.evil.coursecatalog.common.*
import inc.evil.coursecatalog.service.WikipediaApiClient
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@TestcontainersIntegrationTest
@ContextConfiguration(initializers = [WireMockContextInitializer::class])
internal class WikipediaApiClientImplTest : AbstractTestcontainersIntegrationTest() {

    @Autowired
    private lateinit var wikipediaApiClient: WikipediaApiClient

    @Autowired
    private lateinit var wireMockServer: WireMockServer

    @Test
    fun fetchSummaryFor_whenInvoked_returnsResponse() {
        val responseBody = IO.read("/json/wikipediea-response.json")
        wireMockServer.stubResponse("/page/summary/Bruce%20Eckel", responseBody)

        val summary = wikipediaApiClient.fetchSummaryFor("Bruce Eckel")

        Assertions.assertThat(summary).isNotNull
        Assertions.assertThat(summary?.summary).isEqualTo("Bruce Eckel is a computer programmer, author and consultant.")
        Assertions.assertThat(summary?.description).isEqualTo("American computer programmer, author and consultant")
        Assertions.assertThat(summary?.title).isEqualTo("Bruce Eckel")
    }
}
