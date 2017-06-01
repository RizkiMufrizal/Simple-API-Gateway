package org.rizki.mufrizal.api.gateway.BookReview.domain

import javax.persistence.*

/**
 * Created by rizkimufrizal on 5/28/17.
 */

@Entity
@Table(name = "tb_review")
data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        @Column(nullable = false)
        val bookId: Long? = null,
        @Lob
        @Column(nullable = false)
        val reviewText: String? = null
)