package inc.evil.coursecatalog.web.rest

import inc.evil.coursecatalog.common.dto.SuccessResponse
import inc.evil.coursecatalog.service.GreetingsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/greetings")
class GreetingController(val greetingsService: GreetingsService) {

    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable name: String): ResponseEntity<SuccessResponse> {
        return ResponseEntity.ok(SuccessResponse(greetingsService.retrieveGreeting(name)))
    }
}
