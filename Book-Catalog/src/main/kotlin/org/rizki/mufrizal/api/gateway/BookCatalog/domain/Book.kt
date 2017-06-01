package org.rizki.mufrizal.api.gateway.BookCatalog.domain

import javax.persistence.*

/**
 * Created by rizkimufrizal on 5/28/17.
 */
@Entity
@Table(name = "tb_book")
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        @Column(length = 50, nullable = false)
        val title: String? = null,
        @Lob
        @Column(nullable = false)
        val description: String? = null
)