package org.rizki.mufrizal.api.gateway.BookGateway.domain

/**
 * Created by rizkimufrizal on 5/28/17.
 */

data class BookDetail(val id: Long? = null, val title: String? = null, val description: String? = null, val reviews: Iterable<Review>? = null)