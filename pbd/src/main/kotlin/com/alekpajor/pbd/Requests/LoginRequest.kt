package com.alekpajor.pbd.Requests

data class LoginRequest(
    val email: String,
    val password: String,
)