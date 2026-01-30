package com.example.user_service.user

import com.example.user_service.user.UserEntity
import com.example.user_service.user.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val repository: UserRepository) {

    fun createUser(user: UserEntity): UserEntity = repository.save(user)

    fun getAllUsers(): List<UserEntity> = repository.findAll()

    fun getUserById(id: Long): Optional<UserEntity> = repository.findById(id)

    fun updateUser(id: Long, updatedUser: UserEntity): UserEntity? {
        return repository.findById(id).map { existingUser ->
            val userToSave = existingUser.copy(
                firstname = updatedUser.firstname,
                lastname = updatedUser.lastname,
                username = updatedUser.username,
                email = updatedUser.email,
                password = updatedUser.password
            )
            repository.save(userToSave)
        }.orElse(null)
    }

    fun deleteUser(id: Long) = repository.deleteById(id)
}
