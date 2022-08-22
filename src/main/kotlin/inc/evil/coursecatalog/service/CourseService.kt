package inc.evil.coursecatalog.service

import inc.evil.coursecatalog.model.Course

interface CourseService {
    fun findAll(): List<Course>
    fun findById(id: Int): Course
    fun save(course: Course): Course
    fun deleteById(id: Int)
}
