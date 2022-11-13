package inc.evil.reviews.web.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReviewRequest(
    @get:NotBlank(message = "must not be blank") val text: String,
    @get:NotBlank(message = "must not be blank") val author: String,
    @get:NotNull(message = "must not be null") val courseId: Int
) {
}
