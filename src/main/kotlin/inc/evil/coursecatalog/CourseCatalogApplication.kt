package inc.evil.coursecatalog

import inc.evil.coursecatalog.model.Category
import inc.evil.coursecatalog.model.Course
import inc.evil.coursecatalog.repo.CourseRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
class CourseCatalogApplication {
    @Bean
    fun commandLineRunner(courseRepository: CourseRepository): CommandLineRunner {
        return CommandLineRunner {
            val course = Course("Kotlin course", Category.MANAGEMENT)
            courseRepository.save(course)
            println(course)
            var fetchedCourse = courseRepository.findById(course.id!!).orElseThrow()
            fetchedCourse.category = Category.DEVELOPMENT
            courseRepository.save(fetchedCourse)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CourseCatalogApplication>(*args)
}
