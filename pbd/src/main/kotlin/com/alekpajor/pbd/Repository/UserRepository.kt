package com.alekpajor.pbd.Repository

import com.alekpajor.pbd.Models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun findByDoctorId(doctorId: Long): List<User>
}