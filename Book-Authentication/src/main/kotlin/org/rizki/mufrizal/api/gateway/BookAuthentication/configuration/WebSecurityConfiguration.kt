package org.rizki.mufrizal.api.gateway.BookAuthentication.configuration

import org.rizki.mufrizal.api.gateway.BookAuthentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*


/**
 * Created by rizkimufrizal on 5/28/17.
 */
@Configuration
@EnableWebSecurity(debug = true)
class WebSecurityConfiguration @Autowired constructor(val userRepository: UserRepository) : WebSecurityConfigurerAdapter() {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun userDetailsService() = UserDetailsService {
        username ->
        userRepository.findByUsername(username)
                .map { user ->
                    buildUserForAuthentication(user, buildUserAuthority(userRoles = user.roles as List<String>))
                }
                .orElseThrow {
                    UsernameNotFoundException("username not found $username")
                }
    }

    private fun buildUserForAuthentication(user: org.rizki.mufrizal.api.gateway.BookAuthentication.domain.User, grantedAuthorities: List<GrantedAuthority>): User {
        return User(user.username, user.password, user.isActive as Boolean, true, true, true, grantedAuthorities)
    }

    private fun buildUserAuthority(userRoles: List<String>): List<GrantedAuthority> {
        val grantedAuthorities = HashSet<GrantedAuthority>()
        userRoles.forEach { grantedAuthorities.add(SimpleGrantedAuthority(it)) }
        return ArrayList(grantedAuthorities)
    }

    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder?) {
        authenticationManagerBuilder
                ?.userDetailsService(this.userDetailsService())
                ?.passwordEncoder(passwordEncoder())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

}