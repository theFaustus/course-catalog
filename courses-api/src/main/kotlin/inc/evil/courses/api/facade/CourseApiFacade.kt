package inc.evil.courses.api.facade

import inc.evil.courses.api.web.dto.CourseApiResponse

interface CourseApiFacade {
    fun findAll(): List<CourseApiResponse>
    fun findById(id: Int): CourseApiResponse
}
