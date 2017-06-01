package org.rizki.mufrizal.api.gateway.BookAuthentication.repository

import org.rizki.mufrizal.api.gateway.BookAuthentication.domain.OAuth2ClientDetail
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by rizkimufrizal on 5/28/17.
 */
interface OAuth2ClientDetailRepository : PagingAndSortingRepository<OAuth2ClientDetail, String>