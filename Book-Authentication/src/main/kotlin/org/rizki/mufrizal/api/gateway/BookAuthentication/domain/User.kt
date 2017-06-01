package org.rizki.mufrizal.api.gateway.BookAuthentication.domain

import javax.persistence.*

/**
 * Created by rizkimufrizal on 5/28/17.
 */

@Entity
@Table(name = "tb_user")
data class User(
        @Id
        @Column(length = 50, nullable = false)
        val username: String? = null,
        @Column(length = 100, nullable = false)
        val password: String? = null,
        @Column(name = "is_active", length = 100, nullable = false)
        val isActive: Boolean? = null,
        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "roles", joinColumns = arrayOf(JoinColumn(name = "username")))
        @Column(name = "role")
        val roles: List<String>? = null
)