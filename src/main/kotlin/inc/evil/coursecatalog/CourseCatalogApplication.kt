package inc.evil.coursecatalog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
class CourseCatalogApplication

fun main(args: Array<String>) {
    runApplication<CourseCatalogApplication>(*args)
}
