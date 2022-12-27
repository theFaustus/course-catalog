package inc.evil.coursecatalog.repo;

import inc.evil.coursecatalog.model.Instructor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InstructorRepository : JpaRepository<Instructor, Int> {
}
