package org.rizki.mufrizal.api.gateway.BookReview.repository

import org.rizki.mufrizal.api.gateway.BookReview.domain.Review
import org.springframework.data.repository.PagingAndSortingRepository


/**
 * Created by rizkimufrizal on 5/28/17.
 */
interface ReviewRepository : PagingAndSortingRepository<Review, Long> {
    fun findByBookId(bookId: Long?): Iterable<Review>
}