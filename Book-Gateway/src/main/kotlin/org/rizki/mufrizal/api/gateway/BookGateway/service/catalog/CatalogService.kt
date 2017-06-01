package org.rizki.mufrizal.api.gateway.BookGateway.service.catalog

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.rizki.mufrizal.api.gateway.BookGateway.domain.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.stereotype.Component
import rx.Observable

/**
 * Created by rizkimufrizal on 5/28/17.
 */
@Component
class CatalogService @Autowired constructor(val oAuth2RestTemplate: OAuth2RestTemplate) {

    @HystrixCommand(fallbackMethod = "fallbackBook")
    fun getBook(id: Long): Observable<Book> {
        return Observable.create { observer ->
            try {
                observer.onNext(oAuth2RestTemplate.getForObject("http://localhost:8081/api/books/{id}", Book::class.java, id))
                observer.onCompleted()
            } catch (e: Exception) {
                observer.onError(e)
            }
        }
    }

    private fun fallbackBook(id: Long): Book {
        return Book(
                id = id,
                description = "Service Book Lagi error",
                title = "Microservice"
        )
    }
}