package inc.evil.coursecatalog.facade.impl

import inc.evil.coursecatalog.facade.CourseFacade
import inc.evil.coursecatalog.model.Category
import inc.evil.coursecatalog.model.Course
import inc.evil.coursecatalog.service.CourseService
import inc.evil.coursecatalog.web.dto.CourseRequest
import inc.evil.coursecatalog.web.dto.CourseResponse
import org.springframework.stereotype.Component

@Component
class CourseFacadeImpl(val courseService: CourseService) : CourseFacade {
    override fun findAll(): List<CourseResponse> = courseService.findAll().map { CourseResponse.from(it) }

    override fun findById(id: Int): CourseResponse = CourseResponse.from(courseService.findById(id))

    override fun save(courseRequest: CourseRequest): CourseResponse =
        CourseResponse.from(courseService.save(Course(courseRequest.name, Category.valueOf(courseRequest.category))))

    override fun deleteById(id: Int) = courseService.deleteById(id)
}
