package inc.evil.reviews.model

import org.springframework.beans.factory.annotation.Value

interface AuthoredTextProjection {
    fun getCourseId(): Int?
    @Value("#{target.text + ' - ' + target.author}")
    fun getAuthoredText(): String?
}
