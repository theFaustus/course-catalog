package inc.evil.coursecatalog.repo;

import inc.evil.coursecatalog.model.Category
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import java.util.stream.Stream

@DataJpaTest
class CourseRepositoryIntegrationTest {

    @Autowired
    lateinit var courseRepository: CourseRepository

    @Test
    @Sql(scripts = ["/test-data/courses.sql"])
    fun findAllByName() {
        assertThat(courseRepository.findAllByName("Kotlin course")).hasSize(1)
    }

    @Test
    @Sql(scripts = ["/test-data/courses.sql"])
    fun findAllByCategory() {
        assertThat(courseRepository.findAllByCategory(Category.DEVELOPMENT)).hasSize(2)
    }

    @ParameterizedTest
    @MethodSource("categoryAndSize")
    @Sql(scripts = ["/test-data/courses.sql"])
    fun findAllByCategoryParametrized(category: Category, size: Int) {
        assertThat(courseRepository.findAllByCategory(category)).hasSize(size)
    }

    companion object {
        @JvmStatic
        fun categoryAndSize(): Stream<Arguments> {
            return Stream.of(Arguments.of(Category.DEVELOPMENT, 2), Arguments.of(Category.MANAGEMENT, 1))
        }
    }
}
