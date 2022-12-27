package inc.evil.coursecatalog.common

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.apache.commons.io.IOUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.nio.charset.StandardCharsets

fun WireMockServer.stubResponse(url: String, responseBody: String, responseStatus: Int = HttpStatus.OK.value()) {
    this.stubFor(
        WireMock.get(WireMock.urlEqualTo(url))
            .willReturn(
                WireMock.aResponse()
                    .withStatus(responseStatus)
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .withBody(responseBody)
            )
    )
}

class IO {
    companion object {
        fun read(path: String) = IOUtils.toString(this::class.java.getResourceAsStream(path), StandardCharsets.UTF_8.name())
    }
}

