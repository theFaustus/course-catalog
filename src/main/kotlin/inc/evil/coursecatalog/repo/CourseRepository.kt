package inc.evil.coursecatalog.repo;

import inc.evil.coursecatalog.model.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Int>
