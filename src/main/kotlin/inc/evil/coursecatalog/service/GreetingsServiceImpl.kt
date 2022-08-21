package inc.evil.coursecatalog.service


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GreetingsServiceImpl(@Value("\${greeting.message}") val greetingMessage: String) : GreetingsService {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun retrieveGreeting(name: String): String {
        log.info("Retrieving greeting for name = $name")
        return "$greetingMessage $name!"
    }
}
