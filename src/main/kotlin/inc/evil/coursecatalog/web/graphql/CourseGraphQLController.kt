package inc.evil.coursecatalog.web.graphql

import inc.evil.coursecatalog.facade.CourseFacade
import inc.evil.coursecatalog.web.dto.CourseRequest
import inc.evil.coursecatalog.web.dto.CourseResponse
import inc.evil.coursecatalog.web.dto.InstructorResponse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import javax.validation.Valid


@Controller
class CourseGraphQLController(val courseFacade: CourseFacade) {

    @QueryMapping
    fun getCourseById(@Argument id: Int): CourseResponse = courseFacade.findById(id)

    @QueryMapping
    fun getAllCourses(): List<CourseResponse> = courseFacade.findAll()

    @SchemaMapping(typeName = "CourseResponse", field = "instructor")
    fun getInstructor(course: CourseResponse): InstructorResponse = course.instructor

    @MutationMapping
    fun deleteCourseById(@Argument id: Int) = courseFacade.deleteById(id)

    @MutationMapping
    fun createCourse(@Valid @Argument request: CourseRequest): CourseResponse = courseFacade.save(request)
}

