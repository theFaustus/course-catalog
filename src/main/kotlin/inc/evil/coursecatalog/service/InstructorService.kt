package inc.evil.coursecatalog.service

import inc.evil.coursecatalog.model.Instructor

interface InstructorService {
    fun findAll(): List<Instructor>
    fun findById(id: Int): Instructor
    fun save(course: Instructor): Instructor
    fun deleteById(id: Int)
}
