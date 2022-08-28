package inc.evil.coursecatalog.web.dto

import inc.evil.coursecatalog.model.Course

data class CourseResponse(
    val id: Int?,
    val name: String,
    val category: String,
    val createdAt: String,
    val updatedAt: String,
    val instructor: InstructorResponse
) {
    companion object {
        fun from(course: Course): CourseResponse = course.let {
            CourseResponse(
                it.id,
                it.name,
                it.category.toString(),
                it.createdAt.toString(),
                it.updatedAt.toString(),
                InstructorResponse.from(it.instructor)
            )
        }
    }
}
