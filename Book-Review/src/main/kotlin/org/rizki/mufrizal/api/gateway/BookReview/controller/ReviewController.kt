package org.rizki.mufrizal.api.gateway.BookReview.controller

import org.rizki.mufrizal.api.gateway.BookReview.repository.ReviewRepository
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
class ReviewController @Autowired constructor(val reviewRepository: ReviewRepository) {

    @GetMapping(value = "/reviews")
    fun reviews(pageable: Pageable): ResponseEntity<*> = ResponseEntity(reviewRepository.findAll(pageable), HttpStatus.OK)

    @GetMapping(value = "/reviews/{id}")
    fun review(@PathVariable("id") id: Long): ResponseEntity<*> = ResponseEntity(reviewRepository.findOne(id), HttpStatus.OK)

    @GetMapping(value = "/book/reviews/{id}")
    fun reviewBook(@PathVariable("id") id: Long): ResponseEntity<*> = ResponseEntity(reviewRepository.findByBookId(id), HttpStatus.OK)
}