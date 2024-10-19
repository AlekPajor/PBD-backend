package com.alekpajor.pbd.Models

import com.alekpajor.pbd.Common.Role
import jakarta.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val firstName: String,

    val lastName: String,

    val email: String,

    val password: String,

    val doctorId: Long? = null,

    @ManyToOne
    @JoinColumn(name = "activity_id")
    val currentActivity: Activity? = null,

    @Enumerated(EnumType.STRING)
    val role: Role
)
