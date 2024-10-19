package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Models.User
import com.alekpajor.pbd.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired val userRepository: UserRepository,
) {
    fun getAllUsers(): List<User> = userRepository.findAll()

    fun createUser(user: User): User = userRepository.save(user)
}