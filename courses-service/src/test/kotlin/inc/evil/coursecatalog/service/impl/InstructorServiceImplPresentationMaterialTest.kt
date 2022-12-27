package inc.evil.coursecatalog.service.impl

import com.fasterxml.jackson.databind.json.JsonMapper
import inc.evil.coursecatalog.common.IO
import inc.evil.coursecatalog.model.Instructor
import inc.evil.coursecatalog.repo.InstructorRepository
import inc.evil.coursecatalog.service.WikipediaApiClient
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import java.util.*
import java.util.function.Function

internal class InstructorServiceImplPresentationMaterialTest {

    @Test
    fun save() {
        val instructor = Instructor("Mike", courses = mutableListOf())
        val instructorRepository = InstructorRepositoryMock(instructor, 1)
        val instructorServiceImpl = InstructorServiceImpl(FakeWikipediaApiClient(), instructorRepository)
        val savedInstructor = instructorServiceImpl.save(instructor)
        instructorRepository.verify()
        Assertions.assertThat(savedInstructor.description).isEqualTo("American computer programmer, author and consultant")
        Assertions.assertThat(savedInstructor.summary).isEqualTo("Bruce Eckel is a computer programmer, author and consultant.")
    }

    class FakeWikipediaApiClient : WikipediaApiClient {
        override fun fetchSummaryFor(title: String): WikipediaApiClientImpl.WikipediaSummary? {
            return JsonMapper().readValue(
                IO.read("/json/wikipediea-instructor-response.json"),
                WikipediaApiClientImpl.WikipediaSummary::class.java
            )
        }
    }

    class InstructorRepositoryMock(val expectedInstructor: Instructor, val expectInvocations: Int) : InstructorRepository {
        var idInvocationsMap: HashMap<Instructor, Int> = HashMap()
        override fun <S : Instructor?> save(entity: S): S {
            idInvocationsMap.merge(entity!!, 1, Integer::sum)
            return entity
        }

        fun verify() = Assertions.assertThat(idInvocationsMap).containsEntry(expectedInstructor, expectInvocations)


        override fun <S : Instructor?> saveAll(entities: MutableIterable<S>): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun findAll(): MutableList<Instructor> {
            TODO("Not yet implemented")
        }

        override fun findAll(sort: Sort): MutableList<Instructor> {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?> findAll(example: Example<S>): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun findAll(pageable: Pageable): Page<Instructor> {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
            TODO("Not yet implemented")
        }

        override fun findAllById(ids: MutableIterable<Int>): MutableList<Instructor> {
            TODO("Not yet implemented")
        }

        override fun count(): Long {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?> count(example: Example<S>): Long {
            TODO("Not yet implemented")
        }

        override fun delete(entity: Instructor) {
            TODO("Not yet implemented")
        }

        override fun deleteAllById(ids: MutableIterable<Int>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll(entities: MutableIterable<Instructor>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll() {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?> findOne(example: Example<S>): Optional<S> {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?> exists(example: Example<S>): Boolean {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?, R : Any?> findBy(example: Example<S>, queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>): R {
            TODO("Not yet implemented")
        }

        override fun flush() {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?> saveAndFlush(entity: S): S {
            TODO("Not yet implemented")
        }

        override fun <S : Instructor?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun deleteAllInBatch(entities: MutableIterable<Instructor>) {
            TODO("Not yet implemented")
        }

        override fun deleteAllInBatch() {
            TODO("Not yet implemented")
        }

        override fun deleteAllByIdInBatch(ids: MutableIterable<Int>) {
            TODO("Not yet implemented")
        }

        override fun getReferenceById(id: Int): Instructor {
            TODO("Not yet implemented")
        }

        override fun getById(id: Int): Instructor {
            TODO("Not yet implemented")
        }

        override fun getOne(id: Int): Instructor {
            TODO("Not yet implemented")
        }

        override fun deleteById(id: Int) {
            TODO("Not yet implemented")
        }

        override fun existsById(id: Int): Boolean {
            TODO("Not yet implemented")
        }

        override fun findById(id: Int): Optional<Instructor> {
            TODO("Not yet implemented")
        }
    }
}
