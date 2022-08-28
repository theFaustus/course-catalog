package inc.evil.coursecatalog.service.impl

import inc.evil.coursecatalog.common.exceptions.NotFoundException
import inc.evil.coursecatalog.model.Instructor
import inc.evil.coursecatalog.repo.InstructorRepository
import inc.evil.coursecatalog.service.InstructorService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InstructorServiceImpl(val instructorRepository: InstructorRepository) : InstructorService {
    @Transactional(readOnly = true)
    override fun findAll(): List<Instructor> = instructorRepository.findAll()
    @Transactional(readOnly = true)
    override fun findById(id: Int): Instructor =
        instructorRepository.findById(id).orElseThrow { NotFoundException(Instructor::class, "id", id.toString()) }
    @Transactional
    override fun save(course: Instructor): Instructor = instructorRepository.save(course)
    @Transactional
    override fun deleteById(id: Int) = instructorRepository.delete(findById(id))
}
