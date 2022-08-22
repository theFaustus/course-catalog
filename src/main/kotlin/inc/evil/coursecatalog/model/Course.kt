package inc.evil.coursecatalog.model

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "courses")
class Course(
    val name: String,
    @Enumerated(EnumType.STRING) var category: Category,
) : AbstractEntity() {

    override fun toString(): String {
        return "Course(id=$id, name='$name', category=$category)"
    }
}
