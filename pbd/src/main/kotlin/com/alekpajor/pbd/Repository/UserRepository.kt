package com.alekpajor.pbd.Repository

import com.alekpajor.pbd.Models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    @Query("SELECT u FROM User u WHERE u.doctorId = :doctorId")
    fun findByDoctorId(doctorId: Long): List<User>
}