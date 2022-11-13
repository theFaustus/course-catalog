package inc.evil.coursecatalog.model

import javax.persistence.*

@Entity
@Table(name = "instructors")
class Instructor(
    var name: String,
    var description: String? = null,
    var summary: String? = null,

    @OneToMany(mappedBy = "instructor", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var courses: MutableList<Course> = mutableListOf()
) : AbstractEntity()
