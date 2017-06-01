package org.rizki.mufrizal.api.gateway.BookGateway.controller

import org.rizki.mufrizal.api.gateway.BookGateway.domain.BookDetail
import org.rizki.mufrizal.api.gateway.BookGateway.service.integration.IntegrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import rx.Observable
import rx.Observer

/**
 * Created by rizkimufrizal on 5/28/17.
 */

@RestController
@RequestMapping(value = "/api")
class ApiController @Autowired constructor(val integrationService: IntegrationService) {

    @GetMapping(value = "/books/{id}")
    fun getBookDetails(@PathVariable("id") id: Long): DeferredResult<BookDetail> {
        return toDeferredResult(integrationService.getBookDetails(id))
    }

    private fun toDeferredResult(details: Observable<BookDetail>): DeferredResult<BookDetail> {
        val result = DeferredResult<BookDetail>()
        details.subscribe(object : Observer<BookDetail> {
            override fun onCompleted() {}

            override fun onError(throwable: Throwable) {}

            override fun onNext(bookDetail: BookDetail) {
                result.setResult(bookDetail)
            }
        })
        return result
    }
}