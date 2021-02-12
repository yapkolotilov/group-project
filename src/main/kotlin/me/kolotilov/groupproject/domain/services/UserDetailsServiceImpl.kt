package me.kolotilov.groupproject.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Qualifier(UserDetailsServiceImpl.QUALIFIER)
class UserDetailsServiceImpl : UserDetailsService {

    companion object {
        const val QUALIFIER = "MyUserDetailsService"
    }

    @Autowired
    private lateinit var userService: UserService

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.getByUsername(username)
            ?: throw UsernameNotFoundException("Нет пользователя с логином '$username'!")
        return User(
            user.username, user.password, listOf(
                SimpleGrantedAuthority("ROLE_" + user.role)
            )
        )
    }
}