package com.example.user_service.user

import com.example.user_service.user.UserEntity
import com.example.user_service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @PostMapping
    fun createUser(@RequestBody user: UserEntity): UserEntity = service.createUser(user)

    @GetMapping
    fun getAllUsers(): List<UserEntity> = service.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserEntity> =
        service.getUserById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: UserEntity): ResponseEntity<UserEntity> =
        service.updateUser(id, user)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}
