package org.rizki.mufrizal.api.gateway.BookReview.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore
import org.springframework.security.web.access.channel.ChannelProcessingFilter


/**
 * Created by rizkimufrizal on 5/28/17.
 */
@Configuration
open class OAuth2Configuration {

    @Configuration
    @EnableResourceServer
    protected class ResourceServerConfiguration @Autowired constructor(val jedisConnectionFactory: JedisConnectionFactory) : ResourceServerConfigurerAdapter() {

        private val RESOURCE_ID = "RESOURCE_ID_REVIEW"

        @Bean
        fun tokenStore(): TokenStore {
            return RedisTokenStore(jedisConnectionFactory)
        }

        override fun configure(resourceServerSecurityConfigurer: ResourceServerSecurityConfigurer?) {
            resourceServerSecurityConfigurer
                    ?.tokenStore(tokenStore())
                    ?.resourceId(RESOURCE_ID)
        }

        override fun configure(httpSecurity: HttpSecurity?) {
            httpSecurity
                    ?.authorizeRequests()
                    ?.antMatchers("/**")
                    ?.fullyAuthenticated()
                    ?.and()
                    ?.addFilterBefore(CorsConfiguration(), ChannelProcessingFilter::class.java)
        }

    }
}