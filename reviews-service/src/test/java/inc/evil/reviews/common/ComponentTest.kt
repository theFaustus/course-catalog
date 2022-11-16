package inc.evil.reviews.common

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import ro.orange.eshop.userordermanagement.common.RunSqlExtension

@ActiveProfiles("integration-test")
@AutoConfigureGraphQlTester
@ExtendWith(RunSqlExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Target(AnnotationTarget.CLASS)
annotation class ComponentTest()
