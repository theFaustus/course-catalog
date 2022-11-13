package inc.evil.coursecatalog.common

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent

class WireMockContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val wmServer = WireMockServer(WireMockConfiguration().dynamicPort())
        wmServer.start()
        applicationContext.beanFactory.registerSingleton("wireMock", wmServer)
        applicationContext.addApplicationListener {
            if (it is ContextClosedEvent) {
                wmServer.stop()
            }
        }

        TestPropertyValues
            .of(mapOf("api.wikipedia.url" to "http://localhost:${wmServer.port()}"))
            .applyTo(applicationContext)
    }
}
