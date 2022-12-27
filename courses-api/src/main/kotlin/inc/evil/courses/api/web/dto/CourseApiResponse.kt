package inc.evil.courses.api.web.dto

data class CourseApiResponse(
    val id: Int?,
    val name: String,
    val category: String,
    val programmingLanguage: String,
    val programmingLanguageDescription: String?,
    val createdAt: String,
    val updatedAt: String,
    val instructor: InstructorApiResponse
)
