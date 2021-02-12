package me.kolotilov.groupproject.domain.services

import me.kolotilov.groupproject.database.models.toUser
import me.kolotilov.groupproject.database.models.toUserEntity
import me.kolotilov.groupproject.database.repositories.UserRepository
import me.kolotilov.groupproject.domain.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface UserService {

    fun getByUsername(username: String): User?

    fun create(user: User): User

    fun createAll(vararg users: User)

    fun clear()
}

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun getByUsername(username: String): User? {
        return userRepository.findByUsername(username).orElse(null)?.toUser()
    }

    override fun create(user: User): User {
        return userRepository.save(user.toUserEntity()).toUser()
    }

    override fun createAll(vararg users: User) {
        userRepository.saveAll(users.map { it.toUserEntity() })
    }

    override fun clear() {
        userRepository.deleteAll()
    }
}