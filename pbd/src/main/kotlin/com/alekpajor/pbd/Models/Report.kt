package com.alekpajor.pbd.Models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Report(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val patientId: Long,

    val activityId: Long,

    val date: String,

    val time: String,

    val correctness: Long,
)