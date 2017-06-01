package org.rizki.mufrizal.api.gateway.BookGateway.configuration

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.LoggerFactory

/**
 * Created by rizkimufrizal on 6/2/17.
 */
class ZuulFilterConfiguration : ZuulFilter() {

    private val logger = LoggerFactory.getLogger(ZuulFilterConfiguration::class.java)

    override fun run(): Any? {
        val requestContext = RequestContext.getCurrentContext()
        val httpServlerRequest = requestContext.request
        requestContext.addZuulRequestHeader("Authorization", httpServlerRequest.getHeader("Authorization"))

        logger.info(String.format("${httpServlerRequest.method} request to ${httpServlerRequest.requestURL}"))
        logger.info(String.format("http header Authorization ${httpServlerRequest.getHeader("Authorization")}"))
        return null
    }

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun filterType(): String {
        return "pre"
    }

    override fun filterOrder(): Int {
        return 1
    }
}