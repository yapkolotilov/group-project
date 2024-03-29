package me.kolotilov.groupproject.configuration.authorization

import me.kolotilov.groupproject.domain.services.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Qualifier(JwtRequestFilter.QUALIFIER)
class JwtRequestFilter : OncePerRequestFilter() {

    companion object {
        const val QUALIFIER = "JwtRequestFilter"
    }

    @Autowired
    @Qualifier(UserDetailsServiceImpl.QUALIFIER)
    private lateinit var userDetailsService: UserDetailsService
    @Autowired
    private lateinit var jwtUtils: JwtUtils

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")
        var username: String? = null
        var jwt: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            jwt = authorizationHeader.substring(7)
            username = jwtUtils.extractUsername(jwt)
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val details = userDetailsService.loadUserByUsername(username)
            if (jwtUtils.validateToken(jwt ?: "", details)) {
                val token = UsernamePasswordAuthenticationToken(details, null, details.authorities).apply {
                    setDetails(WebAuthenticationDetailsSource().buildDetails(request))
                }
                SecurityContextHolder.getContext().authentication = token
            }
        }
        logger.debug("REQUEST: $request")
        logger.debug("RESPONSE: $response")
        filterChain.doFilter(request, response)
    }
}