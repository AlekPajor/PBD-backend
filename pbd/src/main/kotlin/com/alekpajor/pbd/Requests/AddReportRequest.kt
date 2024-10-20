package com.alekpajor.pbd.Requests

data class AddReportRequest(
    val patientId: Long,
    val activityId: Long,
    val date: String,
    val time: String,
    val correctness: Long
)