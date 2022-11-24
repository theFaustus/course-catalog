package inc.evil.reviews

import inc.evil.reviews.service.ReportGenerationService
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class ReviewsApplication {
    @Bean
    fun init(reportGenerationService: ReportGenerationService): CommandLineRunner {
        return CommandLineRunner {
            runBlocking {
                reportGenerationService.generateReport()
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ReviewsApplication>(*args)
}
