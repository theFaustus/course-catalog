package inc.evil.coursecatalog.facade

import inc.evil.coursecatalog.web.dto.CourseRequest
import inc.evil.coursecatalog.web.dto.CourseResponse

interface CourseFacade {
    fun findAll(): List<CourseResponse>
    fun findById(id: Int): CourseResponse
    fun save(courseRequest: CourseRequest): CourseResponse
    fun deleteById(id: Int)
}
