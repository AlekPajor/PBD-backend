package com.alekpajor.pbd.Models

import jakarta.persistence.*

@Entity
data class Report(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    val patient: User,

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    val activity: Activity,

    val date: String,

    val time: String,

    val correctness: Long,
)