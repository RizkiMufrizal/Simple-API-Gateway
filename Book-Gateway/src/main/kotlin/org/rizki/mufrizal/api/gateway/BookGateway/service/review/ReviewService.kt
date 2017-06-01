package org.rizki.mufrizal.api.gateway.BookGateway.service.review

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.rizki.mufrizal.api.gateway.BookGateway.domain.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.stereotype.Component
import rx.Observable


/**
 * Created by rizkimufrizal on 5/28/17.
 */
@Component
class ReviewService @Autowired constructor(val restTemplate: OAuth2RestTemplate) {

    @HystrixCommand(fallbackMethod = "fallbackReview")
    fun getReviews(id: Long): Observable<Iterable<Review>> {
        return Observable.create { observer ->
            try {
                val responseType = object : ParameterizedTypeReference<Iterable<Review>>() {}
                observer.onNext(restTemplate.exchange("http://localhost:8082/api/book/reviews/{id}", HttpMethod.GET, null, responseType, id).body)
                observer.onCompleted()
            } catch (e: Exception) {
                observer.onError(e)
            }
        }
    }

    private fun fallbackReview(id: Long): Iterable<Review> {
        return listOf()
    }
}