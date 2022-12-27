package inc.evil.coursecatalog.facade

import inc.evil.coursecatalog.web.dto.InstructorRequest
import inc.evil.coursecatalog.web.dto.InstructorResponse

interface InstructorFacade {
    fun findAll(): List<InstructorResponse>
    fun findById(id: Int): InstructorResponse
    fun save(instructorRequest: InstructorRequest): InstructorResponse
    fun deleteById(id: Int)
}
