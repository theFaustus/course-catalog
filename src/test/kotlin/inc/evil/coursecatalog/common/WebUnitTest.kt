package inc.evil.coursecatalog.common

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("unit-test")
@AutoConfigureWebTestClient
@Target(AnnotationTarget.CLASS)
annotation class WebUnitTest()
