package inc.evil.coursecatalog.web.dto

import inc.evil.coursecatalog.model.Instructor

data class InstructorResponse(
    val id: Int?,
    val name: String?,
    val summary: String?,
    val description: String?
) {
    companion object {
        fun from(instructor: Instructor?): InstructorResponse = instructor.let { InstructorResponse(it?.id, it?.name, it?.summary, it?.description) }
    }
}
