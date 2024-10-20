package com.alekpajor.pbd.Models

import jakarta.persistence.*

@Entity
data class Report(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne
    @JoinColumn(name = "patient_id")
    val patient: User,

    @OneToOne
    @JoinColumn(name = "activity_id")
    val activity: Activity,

    val date: String,

    val time: String,

    val correctness: Long,
)