package org.rizki.mufrizal.api.gateway.BookCatalog

import org.rizki.mufrizal.api.gateway.BookCatalog.domain.Book
import org.rizki.mufrizal.api.gateway.BookCatalog.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BookCatalogApplication @Autowired constructor(val bookRepository: BookRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        (1..10)
                .filter { bookRepository.findOne(it.toLong()) == null }
                .forEach {
                    bookRepository.save(Book(
                            title = "Judul Buku $it",
                            description = "deskripsi buku ini adalah $it"
                    ))
                }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(BookCatalogApplication::class.java, *args)
}
