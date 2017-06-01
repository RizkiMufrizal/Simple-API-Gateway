package org.rizki.mufrizal.api.gateway.BookAuthentication.repository

import org.rizki.mufrizal.api.gateway.BookAuthentication.domain.User
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

/**
 * Created by rizkimufrizal on 5/28/17.
 */
interface UserRepository : PagingAndSortingRepository<User, String> {
    fun findByUsername(username: String): Optional<User>
}