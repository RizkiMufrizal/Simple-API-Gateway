package org.rizki.mufrizal.api.gateway.BookCatalog.controller

import org.rizki.mufrizal.api.gateway.BookCatalog.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by rizkimufrizal on 5/28/17.
 */
@RestController
@RequestMapping(value = "/api")
class BookController @Autowired constructor(val bookRepository: BookRepository) {

    @GetMapping(value = "/books")
    fun books(pageable: Pageable): ResponseEntity<*> = ResponseEntity(bookRepository.findAll(pageable), HttpStatus.OK)

    @GetMapping(value = "/books/{id}")
    fun book(@PathVariable("id") id: Long): ResponseEntity<*> = ResponseEntity(bookRepository.findOne(id), HttpStatus.OK)

}