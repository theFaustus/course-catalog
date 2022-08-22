package inc.evil.coursecatalog.web.dto

import inc.evil.coursecatalog.model.Course

data class CourseResponse(
    val id: Int?,
    val name: String,
    val category: String,
    val updatedAt: String,
    val createdAt: String
) {
    companion object {
        fun from(course: Course): CourseResponse = CourseResponse(
            course.id,
            course.name,
            course.category.toString(),
            course.createdAt.toString(),
            course.updatedAt.toString()
        )
    }
}
