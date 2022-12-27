package inc.evil.coursecatalog.service.impl

import inc.evil.coursecatalog.InstructorAggregate
import inc.evil.coursecatalog.common.exceptions.NotFoundException
import inc.evil.coursecatalog.model.Instructor
import inc.evil.coursecatalog.repo.InstructorRepository
import inc.evil.coursecatalog.service.InstructorService
import inc.evil.coursecatalog.service.WikipediaApiClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class InstructorServiceImpl(
    val wikipediaApiClient: WikipediaApiClient,
    val instructorRepository: InstructorRepository,
) : InstructorService {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<Instructor> = instructorRepository.findAll()

    @Transactional(readOnly = true)
    override fun findById(id: Int): Instructor =
        instructorRepository.findById(id).orElseThrow { NotFoundException(Instructor::class, "id", id.toString()) }

    @Transactional
    override fun save(instructor: Instructor): Instructor {
        enrichWithSummary(instructor)
        return instructorRepository.save(instructor)
    }

    private fun enrichWithSummary(instructor: Instructor) {
        wikipediaApiClient.fetchSummaryFor(instructor.name)?.let {
            instructor.summary = it.summary
            instructor.description = it.description
        }
    }

    @Transactional
    override fun upsert(legacyInstructor: InstructorAggregate): Instructor {
        if (instructorRepository.findById(legacyInstructor.id).isPresent) {
            val existingInstructor = instructorRepository.findById(legacyInstructor.id).get()
            existingInstructor.name = legacyInstructor.name
            existingInstructor.createdAt = LocalDateTime.ofInstant(legacyInstructor.createdAt, ZoneId.systemDefault())
            existingInstructor.updatedAt = LocalDateTime.ofInstant(legacyInstructor.updatedAt, ZoneId.systemDefault())
            return instructorRepository.save(existingInstructor)
        } else {
            val newInstructor = Instructor(legacyInstructor.name)
            return instructorRepository.save(newInstructor)
        }
    }

    @Transactional
    override fun deleteById(id: Int) = instructorRepository.delete(findById(id))
}
