package inc.evil.coursecatalog.service

import inc.evil.coursecatalog.service.impl.WikipediaApiClientImpl

interface WikipediaApiClient {
    fun fetchSummaryFor(title: String): WikipediaApiClientImpl.WikipediaSummary?
}
