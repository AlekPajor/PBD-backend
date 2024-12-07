package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Common.Role
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

    fun login(email: String, password: String): User {

        if (email.isBlank() || password.isBlank()) {
            throw IllegalArgumentException("All fields (email, password) must be provided")
        }

        try {
            val user = userRepository.findByEmail(email) ?:
                throw IllegalArgumentException("No user with provided email found")

            if(user.password == password) {
                return user
            } else {
                throw IllegalArgumentException("No password incorrect")
            }

        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(e.message)
        }
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        role: String
    ) {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            throw IllegalArgumentException("All fields (first name, last name, email, password) must be provided")
        }

        if (userRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("User with provided email already exists")
        }

        val userRole = try {
            Role.valueOf(role.uppercase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid role: $role")
        }

        val user = User(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            role = userRole
        )
        userRepository.save(user)
    }

    fun getPatientsByDoctorId(doctorId: Long): List<User> {
        userRepository.findById(doctorId).orElseThrow {
            IllegalArgumentException("No user found with ID: $doctorId")
        }

        val patients = userRepository.findByDoctorId(doctorId)
        if (patients.isEmpty()) {
            throw IllegalArgumentException("No patients found for doctor ID: $doctorId")
        }
        return patients
    }

    fun assignPatientToDoctor(patientEmail: String, doctorId: Long) {
        val doctor = userRepository.findById(doctorId).orElseThrow {
            IllegalArgumentException("No doctor found with ID: $doctorId")
        }

        val patient = userRepository.findByEmail(patientEmail)
            ?: throw IllegalArgumentException("No patient found with provided email")

        if (patient.role == Role.DOCTOR || doctor.role == Role.PATIENT) {
            throw IllegalArgumentException("Cannot assign doctor to doctor or patient to patient")
        }

        if(patient.doctorId != null) {
            throw IllegalArgumentException("Patient already assigned to another doctor")
        }

        val updatedPatient = patient.copy(doctorId = doctorId)
        userRepository.save(updatedPatient)
    }

    fun getPatientDetails(patientId: Long): User {
        val patient = userRepository.findById(patientId).orElseThrow {
            IllegalArgumentException("No doctor found with ID: $patientId")
        }

        if(patient.role != Role.PATIENT) {
            throw IllegalArgumentException("User with provided id has not role ${Role.PATIENT}")
        }

        return patient
    }

}