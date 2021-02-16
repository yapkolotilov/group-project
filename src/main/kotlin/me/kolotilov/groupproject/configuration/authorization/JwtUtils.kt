package me.kolotilov.groupproject.configuration.authorization

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.joda.time.DateTime
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class JwtUtils {

    private companion object {

        private const val SECRET_KEY = "secret"
    }

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun extractExpiration(token: String): DateTime {
        return DateTime(extractClaim(token, Claims::getExpiration))
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        return createToken(claims, userDetails.username)
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun createToken(claims: MutableMap<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(DateTime.now().toDate())
            .setExpiration(DateTime.now().plusDays(30).toDate())
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).isBefore(DateTime.now())
    }
}