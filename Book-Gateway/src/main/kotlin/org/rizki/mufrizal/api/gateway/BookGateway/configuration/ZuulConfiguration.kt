package org.rizki.mufrizal.api.gateway.BookGateway.configuration

import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by rizkimufrizal on 6/2/17.
 */

@Configuration
@EnableZuulProxy
class ZuulConfiguration {

    @Bean
    fun zuulFilterConfiguration(): ZuulFilterConfiguration {
        return ZuulFilterConfiguration()
    }

}