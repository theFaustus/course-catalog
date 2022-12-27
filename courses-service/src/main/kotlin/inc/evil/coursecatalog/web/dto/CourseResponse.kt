package inc.evil.coursecatalog.web.dto

import inc.evil.coursecatalog.model.Course

data class CourseResponse(
    val id: Int?,
    val name: String,
    val category: String,
    val programmingLanguage: String,
    val programmingLanguageDescription: String?,
    val createdAt: String,
    val updatedAt: String,
    val instructor: InstructorResponse
) {
    companion object {
        fun from(course: Course): CourseResponse = course.let {
            CourseResponse(
                id = it.id,
                name = it.name,
                category = it.category.toString(),
                programmingLanguage = it.programmingLanguage,
                programmingLanguageDescription = it.programmingLanguageDescription,
                createdAt = it.createdAt.toString(),
                updatedAt = it.updatedAt.toString(),
                instructor = InstructorResponse.from(it.instructor)
            )
        }
    }
}
