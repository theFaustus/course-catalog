package inc.evil.coursecatalog.web

import inc.evil.coursecatalog.facade.CourseFacade
import inc.evil.coursecatalog.web.dto.CourseRequest
import inc.evil.coursecatalog.web.dto.CourseResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/courses")
class CourseController(val courseFacade: CourseFacade) {

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Int): ResponseEntity<CourseResponse> =
        ResponseEntity.ok(courseFacade.findById(id))

    @GetMapping
    fun getAllCourses(): ResponseEntity<List<CourseResponse>> = ResponseEntity.ok(courseFacade.findAll())

    @DeleteMapping("/{id}")
    fun deleteCourseById(@PathVariable id: Int): ResponseEntity<Unit> = ResponseEntity.ok(courseFacade.deleteById(id))

    @PostMapping
    fun createCourse(@Validated @RequestBody request: CourseRequest): ResponseEntity<CourseResponse> {
        val courseResponse = courseFacade.save(request)
        val location = MvcUriComponentsBuilder.fromMethodCall(
            MvcUriComponentsBuilder.on(javaClass).getCourseById(courseResponse.id ?: 0)
        ).build().toUri()
        return ResponseEntity.created(location).body(courseResponse)
    }
}

