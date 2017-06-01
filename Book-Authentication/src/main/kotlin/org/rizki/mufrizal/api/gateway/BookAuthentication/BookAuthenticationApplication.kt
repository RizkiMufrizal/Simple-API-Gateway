package org.rizki.mufrizal.api.gateway.BookAuthentication

import org.rizki.mufrizal.api.gateway.BookAuthentication.domain.OAuth2ClientDetail
import org.rizki.mufrizal.api.gateway.BookAuthentication.domain.User
import org.rizki.mufrizal.api.gateway.BookAuthentication.repository.OAuth2ClientDetailRepository
import org.rizki.mufrizal.api.gateway.BookAuthentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class BookAuthenticationApplication : CommandLineRunner {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var oAuth2ClientDetailRepository: OAuth2ClientDetailRepository

    override fun run(vararg args: String?) {
        val username = "rizki"
        val clientIdBook = "CLIENT_ID_BOOK"
        val clientIdReview = "CLIENT_ID_REVIEW"
        val clientIdPassword = "CLIENT_ID_PASSWORD"
        val clientIdGateway = "CLIENT_ID_GATEWAY"

        val user = userRepository.findOne(username)
        if (user == null) {
            val roles = listOf("ROLE_USER")
            val newUser = User(
                    username = username,
                    isActive = true,
                    password = BCryptPasswordEncoder().encode("mufrizal"),
                    roles = roles
            )
            userRepository.save(newUser)
        }

        val book = oAuth2ClientDetailRepository.findOne(clientIdBook)
        if (book == null) {
            oAuth2ClientDetailRepository
                    .save(OAuth2ClientDetail(
                            clientId = clientIdBook,
                            accessTokenValidity = 3600,
                            refreshTokenValidity = 3600,
                            additionalInformation = "{\"additional_param\":\"OAuth Book\"}",
                            authorizedGrantTypes = "client_credentials",
                            autoApprove = true,
                            webServerRedirectUri = " ",
                            scope = "read,write",
                            Authorities = " ",
                            resourceIds = "RESOURCE_ID_REVIEW",
                            clientSecret = "CLIENT_SECRET_BOOK"
                    ))
        }

        val review = oAuth2ClientDetailRepository.findOne(clientIdReview)
        if (review == null) {
            oAuth2ClientDetailRepository
                    .save(OAuth2ClientDetail(
                            clientId = clientIdReview,
                            accessTokenValidity = 3600,
                            refreshTokenValidity = 3600,
                            additionalInformation = "{\"additional_param\":\"OAuth Review\"}",
                            authorizedGrantTypes = "client_credentials",
                            autoApprove = true,
                            webServerRedirectUri = " ",
                            scope = "read,write",
                            Authorities = " ",
                            resourceIds = "RESOURCE_ID_BOOK",
                            clientSecret = "CLIENT_SECRET_REVIEW"
                    ))
        }

        val password = oAuth2ClientDetailRepository.findOne(clientIdPassword)
        if (password == null) {
            oAuth2ClientDetailRepository
                    .save(OAuth2ClientDetail(
                            clientId = clientIdPassword,
                            accessTokenValidity = 3600,
                            refreshTokenValidity = 3600,
                            additionalInformation = "{\"additional_param\":\"OAuth Password\"}",
                            authorizedGrantTypes = "password,refresh_token",
                            autoApprove = true,
                            webServerRedirectUri = " ",
                            scope = "read,write",
                            Authorities = " ",
                            resourceIds = "RESOURCE_ID_GATEWAY",
                            clientSecret = "CLIENT_SECRET_PASSWORD"
                    ))
        }

        val gateway = oAuth2ClientDetailRepository.findOne(clientIdGateway)
        if (gateway == null) {
            oAuth2ClientDetailRepository
                    .save(OAuth2ClientDetail(
                            clientId = clientIdGateway,
                            accessTokenValidity = 3600,
                            refreshTokenValidity = 3600,
                            additionalInformation = "{\"additional_param\":\"OAuth Gateway\"}",
                            authorizedGrantTypes = "client_credentials",
                            autoApprove = true,
                            webServerRedirectUri = " ",
                            scope = "read,write",
                            Authorities = " ",
                            resourceIds = "RESOURCE_ID_BOOK,RESOURCE_ID_REVIEW",
                            clientSecret = "CLIENT_ID_GATEWAY"
                    ))
        }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(BookAuthenticationApplication::class.java, *args)
}
