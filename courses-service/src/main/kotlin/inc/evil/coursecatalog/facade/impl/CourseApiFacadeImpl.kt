package inc.evil.coursecatalog.facade.impl

import inc.evil.coursecatalog.service.CourseService
import inc.evil.courses.api.facade.CourseApiFacade
import inc.evil.courses.api.web.dto.CourseApiResponse
import inc.evil.courses.api.web.dto.InstructorApiResponse
import org.springframework.stereotype.Component

@Component
class CourseApiFacadeImpl(val courseService: CourseService) : CourseApiFacade {
    override fun findAll(): List<CourseApiResponse> = courseService.findAll().map {
        CourseApiResponse(
            id = it.id,
            name = it.name,
            category = it.category.toString(),
            programmingLanguage = it.programmingLanguage,
            programmingLanguageDescription = it.programmingLanguageDescription,
            createdAt = it.createdAt.toString(),
            updatedAt = it.updatedAt.toString(),
            instructor = InstructorApiResponse(it.instructor?.id, it.instructor?.name, it.instructor?.summary, it.instructor?.description)
        )
    }

    override fun findById(id: Int): CourseApiResponse = courseService.findById(id).let {
        CourseApiResponse(
            id = it.id,
            name = it.name,
            category = it.category.toString(),
            programmingLanguage = it.programmingLanguage,
            programmingLanguageDescription = it.programmingLanguageDescription,
            createdAt = it.createdAt.toString(),
            updatedAt = it.updatedAt.toString(),
            instructor = InstructorApiResponse(it.instructor?.id, it.instructor?.name, it.instructor?.summary, it.instructor?.description)
        )
    }

}
