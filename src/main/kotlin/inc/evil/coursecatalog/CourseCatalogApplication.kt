package inc.evil.coursecatalog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CourseCatalogApplication

fun main(args: Array<String>) {
	runApplication<CourseCatalogApplication>(*args)
}
