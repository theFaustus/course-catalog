package inc.evil.coursecatalog.web.dto

import javax.validation.constraints.NotBlank

data class InstructorRequest(
    @get:NotBlank(message = "must not be blank") val name: String,
)
