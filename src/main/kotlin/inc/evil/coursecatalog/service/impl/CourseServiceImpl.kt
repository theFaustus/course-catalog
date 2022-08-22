package inc.evil.coursecatalog.service.impl

import inc.evil.coursecatalog.common.exceptions.NotFoundException
import inc.evil.coursecatalog.model.Course
import inc.evil.coursecatalog.repo.CourseRepository
import inc.evil.coursecatalog.service.CourseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseServiceImpl(val courseRepository: CourseRepository) : CourseService {
    @Transactional(readOnly = true)
    override fun findAll(): List<Course> = courseRepository.findAll()
    @Transactional(readOnly = true)
    override fun findById(id: Int): Course =
        courseRepository.findById(id).orElseThrow { NotFoundException(Course::class, "id", id.toString()) }
    @Transactional
    override fun save(course: Course): Course = courseRepository.save(course)
    @Transactional
    override fun deleteById(id: Int) = courseRepository.delete(findById(id))
}
