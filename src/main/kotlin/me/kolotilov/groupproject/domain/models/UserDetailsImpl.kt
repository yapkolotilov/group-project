package me.kolotilov.groupproject.domain.models

import me.kolotilov.groupproject.database.models.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * UserDetails.
 */
data class UserDetailsImpl(
        private val username: String,
        private val password: String,
        private val authority: GrantedAuthority
) : UserDetails {

    override fun getAuthorities() = listOf(authority)

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}

fun UserEntity.toUserDetailsImpl() = UserDetailsImpl(
        username = username,
        password = password,
        authority = SimpleGrantedAuthority(role)
)