package inc.evil.coursecatalog.web.rest

import inc.evil.coursecatalog.facade.InstructorFacade
import inc.evil.coursecatalog.web.dto.InstructorRequest
import inc.evil.coursecatalog.web.dto.InstructorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/instructors")
class InstructorController(val instructorFacade: InstructorFacade) {

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Int): ResponseEntity<InstructorResponse> =
        ResponseEntity.ok(instructorFacade.findById(id))

    @GetMapping
    fun getAllCourses(): ResponseEntity<List<InstructorResponse>> = ResponseEntity.ok(instructorFacade.findAll())

    @DeleteMapping("/{id}")
    fun deleteCourseById(@PathVariable id: Int): ResponseEntity<Unit> = ResponseEntity.ok(instructorFacade.deleteById(id))

    @PostMapping
    fun createCourse(@Valid @RequestBody request: InstructorRequest): ResponseEntity<InstructorResponse> {
        val courseResponse = instructorFacade.save(request)
        val location = MvcUriComponentsBuilder.fromMethodCall(
            MvcUriComponentsBuilder.on(javaClass).getCourseById(courseResponse.id ?: 0)
        ).build().toUri()
        return ResponseEntity.created(location).body(courseResponse)
    }
}

