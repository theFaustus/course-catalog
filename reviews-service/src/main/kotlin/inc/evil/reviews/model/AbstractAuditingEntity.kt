package inc.evil.reviews.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import java.io.Serializable
import java.time.Instant

@JsonIgnoreProperties(value = ["createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"], allowGetters = true)
abstract class AbstractAuditingEntity<T> : Serializable {
    abstract val id: T

    @Column("created_by")
    var createdBy: String? = null

    @CreatedDate
    @Column("created_date")
    var createdDate = Instant.now()

    @Column("last_modified_by")
    var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column("last_modified_date")
    var lastModifiedDate = Instant.now()

    companion object {
        private const val serialVersionUID = 1L
    }
}
