package me.kolotilov.groupproject.utils

import me.kolotilov.groupproject.domain.models.Role
import me.kolotilov.groupproject.domain.models.User
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

fun restrictRole(user: User, field: Any?, vararg role: Role) {
    if (field != null && !role.contains(user.role))
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Данный запрос недоступен для вашей роли")
}