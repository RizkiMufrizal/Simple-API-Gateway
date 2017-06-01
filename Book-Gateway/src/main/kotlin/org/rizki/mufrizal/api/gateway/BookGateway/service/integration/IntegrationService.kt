package org.rizki.mufrizal.api.gateway.BookGateway.service.integration

import org.rizki.mufrizal.api.gateway.BookGateway.domain.Book
import org.rizki.mufrizal.api.gateway.BookGateway.domain.BookDetail
import org.rizki.mufrizal.api.gateway.BookGateway.service.catalog.CatalogService
import org.rizki.mufrizal.api.gateway.BookGateway.service.review.ReviewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import rx.Observable
import org.rizki.mufrizal.api.gateway.BookGateway.domain.Review


/**
 * Created by rizkimufrizal on 5/28/17.
 */

@Component
class IntegrationService {

    @Autowired
    private lateinit var catalogService: CatalogService

    @Autowired
    private lateinit var reviewService: ReviewService

    fun getBookDetails(bookId: Long): Observable<BookDetail> {
        return Observable.zip(
                catalogService.getBook(bookId),
                reviewService.getReviews(bookId),
                this::buildBookDetails
        )
    }

    private fun buildBookDetails(book: Book, reviews: Iterable<Review>): BookDetail {
        return BookDetail(
                id = book.id,
                title = book.title,
                description = book.description,
                reviews = reviews
        )
    }

}