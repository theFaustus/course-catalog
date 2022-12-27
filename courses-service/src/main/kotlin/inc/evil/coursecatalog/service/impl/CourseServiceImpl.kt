package inc.evil.coursecatalog.service.impl

import com.hazelcast.core.HazelcastInstance
import inc.evil.coursecatalog.common.exceptions.NotFoundException
import inc.evil.coursecatalog.config.hazelcast.HazelcastConfiguration.Companion.WIKIPEDIA_SUMMARIES
import inc.evil.coursecatalog.model.Course
import inc.evil.coursecatalog.repo.CourseRepository
import inc.evil.coursecatalog.service.CourseService
import inc.evil.coursecatalog.service.WikipediaApiClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseServiceImpl(
    val courseRepository: CourseRepository,
    val wikipediaApiClient: WikipediaApiClient,
    @Qualifier("hazelcastInstance") val hazelcastInstance: HazelcastInstance
) : CourseService {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
    @Transactional(readOnly = true)
    override fun findAll(): List<Course> = courseRepository.findAll()

    @Transactional(readOnly = true)
    override fun findById(id: Int): Course =
        courseRepository.findById(id).orElseThrow { NotFoundException(Course::class, "id", id.toString()) }

    @Transactional
    override fun save(course: Course): Course {
        enhanceWithProgrammingLanguageDescription(course)
        return courseRepository.save(course)
    }

    private fun enhanceWithProgrammingLanguageDescription(course: Course) {
        val summaries = hazelcastInstance.getMap<String, WikipediaApiClientImpl.WikipediaSummary>(WIKIPEDIA_SUMMARIES)
        log.debug("Fetched hazelcast cache [$WIKIPEDIA_SUMMARIES] = [${summaries}(${summaries.size})]  ")
        summaries.getOrElse(course.programmingLanguage) {
            wikipediaApiClient.fetchSummaryFor("${course.programmingLanguage}_(programming_language)")?.let {
                log.debug("No cache value found, using wikipedia's response $it to update $course programming language description")
                summaries[course.programmingLanguage] = it
                it
            }
        }?.let { course.programmingLanguageDescription = it.summary }
    }

    @Transactional
    override fun deleteById(id: Int) = courseRepository.delete(findById(id))
}
