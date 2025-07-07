package com.demo.bookapi.model

import jakarta.persistence.*
import io.micronaut.serde.annotation.Serdeable

@Serdeable // Required for JSON serialization
@Entity
data class Book(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	var title: String,
	var author: String
)
