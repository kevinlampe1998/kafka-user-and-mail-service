package com.example.user_service.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_entity")
data class UserEntity (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val firstname: String,

    @Column(nullable = false)
    val lastname: String,

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val password: String
)