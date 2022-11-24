package inc.evil.coursecatalog.service.impl

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import inc.evil.coursecatalog.service.WikipediaApiClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.util.retry.Retry
import java.time.Duration

@Component
class WikipediaApiClientImpl(val wikipediaApiConfig: WikipediaApiConfig) : WikipediaApiClient {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun fetchSummaryFor(title: String): WikipediaSummary? {
        val path = "/page/summary/${title}"
        return WebClient.builder()
            .baseUrl(wikipediaApiConfig.url)
            .clientConnector(ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
            .filter(logRequest())
            .build()
            .get()
            .uri(path)
            .retrieve()
            .onStatus({ it.isError || it.is3xxRedirection }, ::handleError)
            .bodyToMono(WikipediaSummary::class.java)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(3)))
            .onErrorResume { logError(wikipediaApiConfig.url + path, it, "Something happened"); Mono.empty() }
            .block()
            .also { log.info("Received response from wikipedia {$it}") }
    }

    fun logRequest(): ExchangeFilterFunction = ExchangeFilterFunction.ofRequestProcessor {
        log.info("Request {}:{}", it.method(), it.url()); Mono.just(it)
    }


    fun handleError(r: ClientResponse): Mono<Throwable> = r.bodyToMono(String::class.java)
        .flatMap { Mono.error(RuntimeException("Encountered an error with status=[${r.statusCode()}], response body=[${it}]")) }

    fun logError(path: String, it: Throwable, context: String) {
        log.error("[${it.message}] - [$path] - ($context)", it);
    }

    @ConstructorBinding
    @ConfigurationProperties(prefix = "api.wikipedia")
    data class WikipediaApiConfig(val url: String)

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class WikipediaSummary(
        @JsonProperty("title")
        val title: String,
        @JsonProperty("description")
        val description: String,
        @JsonProperty("extract")
        val summary: String
    )


}


