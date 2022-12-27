package inc.evil.coursecatalog.repo;

import inc.evil.coursecatalog.common.AbstractTestcontainersIntegrationTest
import inc.evil.coursecatalog.common.TestcontainersIntegrationTest
import inc.evil.coursecatalog.model.Category
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import java.util.stream.Stream

@TestcontainersIntegrationTest
class CourseRepositoryTestcontainersIntegrationTest : AbstractTestcontainersIntegrationTest() {

    @Autowired
    lateinit var courseRepository: CourseRepository

    @Test
    @Sql(scripts = ["/postgres/courses.sql"])
    fun findAllByName() {
        assertThat(courseRepository.findAllByName("Kotlin course")).hasSize(1)
    }

    @Test
    @Sql(scripts = ["/postgres/courses.sql"])
    fun findAllByInstructorName() {
        assertThat(courseRepository.findAllByInstructorName("Bruce Eckel")).hasSize(3)
    }

    @Test
    @Sql(scripts = ["/postgres/courses.sql"])
    fun findAllByCategory() {
        assertThat(courseRepository.findAllByCategory(Category.TUTORIAL)).hasSize(2)
    }

    @ParameterizedTest
    @MethodSource("categoryAndSize")
    @Sql(scripts = ["/postgres/courses.sql"])
    fun findAllByCategoryParametrized(category: Category, size: Int) {
        assertThat(courseRepository.findAllByCategory(category)).hasSize(size)
    }

    companion object {
        @JvmStatic
        fun categoryAndSize(): Stream<Arguments> {
            return Stream.of(Arguments.of(Category.TUTORIAL, 2), Arguments.of(Category.INTERVIEW, 1))
        }
    }
}
