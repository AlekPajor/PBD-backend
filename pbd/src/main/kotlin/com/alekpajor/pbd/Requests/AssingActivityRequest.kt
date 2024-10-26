package com.alekpajor.pbd.Requests

data class AssignActivityRequest(
    val exerciseId: Long,
    val patientId: Long,
    val duration: String,
    val startingTime: String,
    val period: String,
)
