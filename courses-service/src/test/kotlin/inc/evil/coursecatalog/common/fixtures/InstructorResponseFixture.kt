package inc.evil.coursecatalog.common.fixtures

import inc.evil.coursecatalog.web.dto.InstructorResponse

class InstructorResponseFixture {
    companion object {
        fun of(id: Int = -1, name: String = "Bruce Eckel", summary: String = "summary", description: String = "description") = InstructorResponse(id, name, summary, description)
    }
}
