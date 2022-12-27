package inc.evil.coursecatalog.common.fixtures

import inc.evil.coursecatalog.model.Category
import inc.evil.coursecatalog.web.dto.CourseResponse
import inc.evil.coursecatalog.web.dto.InstructorResponse

class CourseResponseFixture {
    companion object {
        fun of(
            id: Int = -1,
            name: String = "Kotlin course",
            category: String = Category.TUTORIAL.name,
            createdAt: String = "2022-08-22 20:22:36.510984",
            updatedAt: String = "2022-08-22 20:22:36.572486",
            programmingLanguage: String = "Java",
            programmingLanguageDescription: String = "Java",
            instructor: InstructorResponse = InstructorResponseFixture.of()
        ) =
            CourseResponse(id, name, category, programmingLanguage, programmingLanguageDescription, createdAt, updatedAt, instructor)
    }
}
