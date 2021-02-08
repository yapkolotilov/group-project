package me.kolotilov.groupproject.domain.services

import me.kolotilov.groupproject.database.repositories.UserRepository
import me.kolotilov.groupproject.domain.models.toUserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.getByUsername(username).toUserDetailsImpl()
    }
}