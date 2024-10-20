package com.alekpajor.pbd.Requests

data class AssignPatientRequest(
    val patientEmail: String,
    val doctorId: Long
)
