package ro.orange.eshop.userordermanagement.common

import inc.evil.reviews.config.r2dbc.R2dbcConfiguration
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import

@DataR2dbcTest
@Tag("integration")
@Target(AnnotationTarget.CLASS)
@Import(R2dbcConfiguration::class)
@ExtendWith(RunSqlExtension::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
annotation class RepositoryIntegrationTest()
