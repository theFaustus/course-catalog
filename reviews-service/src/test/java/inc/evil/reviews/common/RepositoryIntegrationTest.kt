package ro.orange.eshop.userordermanagement.common

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

@DataR2dbcTest
@Tag("integration")
@Target(AnnotationTarget.CLASS)
@ExtendWith(RunSqlExtension::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
annotation class RepositoryIntegrationTest()
