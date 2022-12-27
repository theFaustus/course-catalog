package inc.evil.coursecatalog.facade.impl

import inc.evil.coursecatalog.facade.InstructorFacade
import inc.evil.coursecatalog.model.Instructor
import inc.evil.coursecatalog.service.InstructorService
import inc.evil.coursecatalog.web.dto.InstructorRequest
import inc.evil.coursecatalog.web.dto.InstructorResponse
import org.springframework.stereotype.Component

@Component
class InstructorFacadeImpl(val instructorService: InstructorService) : InstructorFacade {
    override fun findAll(): List<InstructorResponse> = instructorService.findAll().map { InstructorResponse.from(it) }

    override fun findById(id: Int): InstructorResponse = InstructorResponse.from(instructorService.findById(id))

    override fun save(instructorRequest: InstructorRequest): InstructorResponse =
        InstructorResponse.from(instructorService.save(Instructor(instructorRequest.name)))

    override fun deleteById(id: Int) = instructorService.deleteById(id)
}
