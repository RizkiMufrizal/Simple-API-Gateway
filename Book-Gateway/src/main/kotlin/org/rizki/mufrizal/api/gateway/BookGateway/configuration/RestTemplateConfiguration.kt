package org.rizki.mufrizal.api.gateway.BookGateway.configuration

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.web.client.RestTemplate

/**
 * Created by rizkimufrizal on 5/28/17.
 */

@Configuration
@RibbonClient(name = RibbonConfiguration.name, configuration = arrayOf(RibbonConfiguration::class))
@EnableOAuth2Client
class RestTemplateConfiguration {

    @LoadBalanced
    @Bean
    fun getRestTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun resource(): OAuth2ProtectedResourceDetails {
        val clientCredentials = ClientCredentialsResourceDetails()
        clientCredentials.clientId = "CLIENT_ID_GATEWAY"
        clientCredentials.clientSecret = "CLIENT_SECRET_GATEWAY"
        clientCredentials.grantType = "client_credentials"
        clientCredentials.accessTokenUri = "http://localhost:8083/oauth/token"
        return clientCredentials
    }

    @Bean
    fun restTemplate(): OAuth2RestTemplate {
        val accessTokenRequest = DefaultAccessTokenRequest()
        return OAuth2RestTemplate(resource(), DefaultOAuth2ClientContext(accessTokenRequest))
    }

}