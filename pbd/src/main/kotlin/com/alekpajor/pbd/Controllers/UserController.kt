package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.User
import com.alekpajor.pbd.Requests.AssignPatientRequest
import com.alekpajor.pbd.Requests.LoginRequest
import com.alekpajor.pbd.Requests.RegisterRequest
import com.alekpajor.pbd.Services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(@Autowired val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    @PostMapping
    fun createUser(@RequestBody user: User): User {
        return userService.createUser(user)
    }

    @GetMapping("login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        return try {
            val user = userService.login(
                email = loginRequest.email,
                password = loginRequest.password
            )
            ResponseEntity(user, HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}. \nLogin failed.", HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("register")
    fun registerUser(@RequestBody registerRequest: RegisterRequest): ResponseEntity<Any> {
        return try {
            userService.register(
                firstName = registerRequest.firstName,
                lastName = registerRequest.lastName,
                email = registerRequest.email,
                password = registerRequest.password,
                role = registerRequest.role
            )
            ResponseEntity("User registered as ${registerRequest.role.uppercase()}", HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}. \nYou have not been registered.", HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("patients/{doctorId}")
    fun getPatientsByDoctorId(@PathVariable doctorId: Long): ResponseEntity<Any> {
        return try {
            val patients = userService.getPatientsByDoctorId(doctorId)
            ResponseEntity(patients, HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}", HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("assign")
    fun assignPatientToDoctor(@RequestBody assignPatientRequest: AssignPatientRequest): ResponseEntity<Any> {
        return try {
            userService.assignPatientToDoctor(
                patientEmail = assignPatientRequest.patientEmail,
                doctorId = assignPatientRequest.doctorId
            )
            ResponseEntity("Patient with email ${assignPatientRequest.patientEmail} assigned to doctor with ID: ${assignPatientRequest.doctorId}", HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}", HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("details/{patientId}")
    fun getPatientDetails(@PathVariable patientId: Long): ResponseEntity<Any> {
        return try {
            val patient = userService.getPatientDetails(patientId)
            ResponseEntity(patient, HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}", HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}