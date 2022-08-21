package inc.evil.coursecatalog.web

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
    fun retrieveGreeting(@PathVariable name: String): ResponseEntity<String> {
        return ResponseEntity.ok(greetingsService.retrieveGreeting(name))
    }
}
