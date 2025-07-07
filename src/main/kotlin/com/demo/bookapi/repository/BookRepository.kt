package com.demo.bookapi.repository

import com.demo.bookapi.model.Book
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface BookRepository : JpaRepository<Book, Long>