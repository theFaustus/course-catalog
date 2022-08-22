package inc.evil.coursecatalog.web.dto

import javax.validation.constraints.NotBlank

data class CourseRequest(
    @get:NotBlank(message = "must not be blank") val name: String,
    @get:NotBlank(message = "must not be blank") val category: String
)
