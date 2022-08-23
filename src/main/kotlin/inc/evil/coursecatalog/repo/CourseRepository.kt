package inc.evil.coursecatalog.repo;

import inc.evil.coursecatalog.model.Category
import inc.evil.coursecatalog.model.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Int> {
    fun findAllByName(name: String): List<Course>
    @Query("select c from Course c where c.category = :category")
    fun findAllByCategory(category: Category): List<Course>
}
