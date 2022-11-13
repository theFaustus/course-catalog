package inc.evil.reviews.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("reviews")
data class Review(
    @Id
    var id: Int? = null,
    var text: String,
    var author: String,
    @Column("created_at")
    @CreatedDate
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate
    @Column("last_modified_at")
    var lastModifiedAt: LocalDateTime? = null,
    @Column("course_id")
    var courseId: Int
)
