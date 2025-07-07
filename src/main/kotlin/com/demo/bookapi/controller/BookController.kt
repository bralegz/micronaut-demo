package com.demo.bookapi.controller

import com.demo.bookapi.model.Book
import com.demo.bookapi.repository.BookRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import jakarta.inject.Inject

@Controller("/books")
class BookController(@Inject private val repository: BookRepository) {

	@Get
	fun getAll(): List<Book> = repository.findAll()

	@Get("/{id}")
	fun getById(@PathVariable id: Long): HttpResponse<Book> =
		repository.findById(id)
			.map { HttpResponse.ok(it) }
			.orElse(HttpResponse.notFound())

	@Post
	fun create(@Body book: Book): HttpResponse<Book> {
		val saved = repository.save(book)
		return HttpResponse.created(saved)
	}

	@Put("/{id}")
	fun update(@PathVariable id: Long, @Body updated: Book): HttpResponse<Book> {
		val existing = repository.findById(id)
		return if (existing.isPresent) {
			val book = existing.get().copy(title = updated.title, author = updated.author)
			HttpResponse.ok(repository.update(book))
		} else {
			HttpResponse.notFound()
		}
	}

	@Delete("/{id}")
	fun delete(@PathVariable id: Long): HttpResponse<Void> {
		return if (repository.existsById(id)) {
			repository.deleteById(id)
			HttpResponse.noContent()
		} else {
			HttpResponse.notFound()
		}
	}
}