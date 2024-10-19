package com.alekpajor.pbd.Repository

import com.alekpajor.pbd.Models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>