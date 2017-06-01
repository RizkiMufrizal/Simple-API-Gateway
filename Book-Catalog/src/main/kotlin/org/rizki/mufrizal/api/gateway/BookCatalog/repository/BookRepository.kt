package org.rizki.mufrizal.api.gateway.BookCatalog.repository

import org.rizki.mufrizal.api.gateway.BookCatalog.domain.Book
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by rizkimufrizal on 5/28/17.
 */
interface BookRepository : PagingAndSortingRepository<Book, Long>