package inc.evil.coursecatalog.web.graphql

import inc.evil.coursecatalog.facade.InstructorFacade
import inc.evil.coursecatalog.web.dto.InstructorRequest
import inc.evil.coursecatalog.web.dto.InstructorResponse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import javax.validation.Valid


@Controller
class InstructorGraphQLController(val instructorFacade: InstructorFacade) {

    @QueryMapping
    fun getInstructorById(@Argument id: Int): InstructorResponse = instructorFacade.findById(id)

    @QueryMapping
    fun getAllInstructors(): List<InstructorResponse> = instructorFacade.findAll()

    @MutationMapping
    fun deleteInstructorById(@Argument id: Int) = instructorFacade.deleteById(id)

    @MutationMapping
    fun createInstructor(@Valid @Argument request: InstructorRequest): InstructorResponse = instructorFacade.save(request)
}

