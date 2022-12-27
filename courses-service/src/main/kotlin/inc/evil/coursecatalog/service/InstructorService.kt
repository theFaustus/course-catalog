package inc.evil.coursecatalog.service

import inc.evil.coursecatalog.InstructorAggregate
import inc.evil.coursecatalog.model.Instructor

interface InstructorService {
    fun findAll(): List<Instructor>
    fun findById(id: Int): Instructor
    fun save(instructor: Instructor): Instructor
    fun upsert(legacyInstructor: InstructorAggregate): Instructor
    fun deleteById(id: Int)
}
