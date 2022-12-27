package inc.evil.coursecatalog.model

import javax.persistence.*

@Entity
@Table(name = "instructors")
class Instructor(
    var name: String,
    @Column(name = "description", length = 3000)
    var description: String? = null,
    @Column(name = "summary", length = 3000)
    var summary: String? = null,

    @OneToMany(mappedBy = "instructor", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var courses: MutableList<Course> = mutableListOf()
) : AbstractEntity()
