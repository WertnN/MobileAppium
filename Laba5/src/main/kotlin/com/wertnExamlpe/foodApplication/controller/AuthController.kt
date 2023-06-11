package com.wertnExamlpe.foodApplication.controller

import com.wertnExamlpe.foodApplication.dto.LoginDto
import com.wertnExamlpe.foodApplication.dto.Message
import com.wertnExamlpe.foodApplication.dto.RegisterDto
import com.wertnExamlpe.foodApplication.model.User
import com.wertnExamlpe.foodApplication.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.crypto.SecretKey



@RestController
@RequestMapping("api")
class AuthController(private val service: UserService) {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDto): ResponseEntity<User> {
        val user = User()
        user.name = body.name
        user.email = body.email
        user.password = body.password
        return ResponseEntity.ok(this.service.save(user))
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDto, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.service.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body(Message("user not found!"))

        if (!user.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body(Message("invalid password!"))
        }

        val issuer = user.id.toString()

        val key: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(key).compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Logged in"))
    }

}