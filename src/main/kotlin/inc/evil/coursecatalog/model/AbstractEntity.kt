package inc.evil.coursecatalog.model

import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    @Column(name = "created_at", nullable = false) @CreatedDate var createdAt: LocalDateTime? = null
    @Column(name = "updated_at", nullable = false) @LastModifiedDate var updatedAt: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as AbstractEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

}
