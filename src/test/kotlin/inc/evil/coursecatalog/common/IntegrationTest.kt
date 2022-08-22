package inc.evil.coursecatalog.common

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
@AutoConfigureWebTestClient
@AutoConfigureTestDatabase
@TestExecutionListeners(
    listeners = [
        DependencyInjectionTestExecutionListener::class,
        MockitoTestExecutionListener::class,
        SqlScriptsTestExecutionListener::class,
        TransactionalTestExecutionListener::class]
)
@Target(AnnotationTarget.CLASS)
annotation class IntegrationTest()
