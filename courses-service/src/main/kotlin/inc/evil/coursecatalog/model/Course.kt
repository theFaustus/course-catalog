package inc.evil.coursecatalog.model

import javax.persistence.*

@Entity
@Table(name = "courses")
class Course(
    var name: String,
    @Column(name = "programming_language")
    var programmingLanguage: String,
    @Column(name = "programming_language_description", length = 3000, nullable = true)
    var programmingLanguageDescription: String? = null,
    @Enumerated(EnumType.STRING)
    var category: Category,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    var instructor: Instructor? = null
) : AbstractEntity() {

    override fun toString(): String {
        return "Course(id=$id, name='$name', category=$category)"
    }
}
