package org.rizki.mufrizal.api.gateway.BookReview

import org.rizki.mufrizal.api.gateway.BookReview.domain.Review
import org.rizki.mufrizal.api.gateway.BookReview.repository.ReviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BookReviewApplication @Autowired constructor(val reviewRepository: ReviewRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        (1..10)
                .filter { reviewRepository.findOne(it.toLong()) == null }
                .forEach {
                    for (j in 1..10) {
                        reviewRepository.save(Review(
                                bookId = j.toLong(),
                                reviewText = "ini adalah contoh review no $it-$j"
                        ))
                    }
                }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(BookReviewApplication::class.java, *args)
}
