package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.User
import com.alekpajor.pbd.Services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(@Autowired val userService: UserService) {

    @GetMapping("getall")
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    @PostMapping
    fun createUser(@RequestBody user: User): User {
        return userService.createUser(user)
    }
}