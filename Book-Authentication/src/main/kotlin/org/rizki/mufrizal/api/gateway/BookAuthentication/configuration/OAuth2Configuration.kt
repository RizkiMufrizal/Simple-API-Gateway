package org.rizki.mufrizal.api.gateway.BookAuthentication.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import javax.sql.DataSource
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore
import org.springframework.security.oauth2.provider.token.TokenStore


/**
 * Created by rizkimufrizal on 5/28/17.
 */
@Configuration
open class OAuth2Configuration {

    @Configuration
    @EnableAuthorizationServer
    protected class AuthorizationServerConfiguration : AuthorizationServerConfigurerAdapter() {

        @Autowired
        @Qualifier("authenticationManagerBean")
        private lateinit var authenticationManager: AuthenticationManager

        @Autowired
        private lateinit var dataSource: DataSource

        @Autowired
        private lateinit var jedisConnectionFactory: JedisConnectionFactory

        @Bean
        fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
            return JwtAccessTokenConverter()
        }

        @Bean
        fun tokenStore(): TokenStore {
            return RedisTokenStore(jedisConnectionFactory)
        }

        @Throws(Exception::class)
        override fun configure(authorizationServerEndpointsConfigurer: AuthorizationServerEndpointsConfigurer?) {
            authorizationServerEndpointsConfigurer
                    ?.accessTokenConverter(jwtAccessTokenConverter())
                    ?.tokenStore(tokenStore())
                    ?.authenticationManager(authenticationManager)
        }

        @Throws(Exception::class)
        override fun configure(clientDetailsServiceConfigurer: ClientDetailsServiceConfigurer?) {
            clientDetailsServiceConfigurer
                    ?.jdbc(dataSource)
        }

    }

}