package me.kolotilov.groupproject.presentation

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.kolotilov.groupproject.configuration.authorization.JwtUtils
import me.kolotilov.groupproject.domain.services.UserDetailsServiceImpl
import me.kolotilov.groupproject.presentation.input.LoginDto
import me.kolotilov.groupproject.presentation.output.TokenDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping
class AuthorizationController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Autowired
    @Qualifier(UserDetailsServiceImpl.QUALIFIER)
    private lateinit var userDetailsService: UserDetailsService
    @Autowired
    private lateinit var jwtUtils: JwtUtils

    @ApiOperation("Логин в приложениею")
    @PostMapping("login")
    fun login(
        @ApiParam("Логин и пароль.")
        @RequestBody request: LoginDto
    ): TokenDto {
        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
            val userDetails = userDetailsService.loadUserByUsername(request.username)
            val jwt = jwtUtils.generateToken(userDetails)
            TokenDto(jwt)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неправильные логин или пароль")
        }
    }
}