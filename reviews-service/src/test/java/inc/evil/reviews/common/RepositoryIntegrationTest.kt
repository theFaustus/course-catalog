package ro.orange.eshop.userordermanagement.common

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

@DataR2dbcTest
@Target(AnnotationTarget.CLASS)
@ExtendWith(RunSqlExtension::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Tag("integration")
annotation class RepositoryIntegrationTest()
