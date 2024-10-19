package com.alekpajor.pbd.Models

import com.alekpajor.pbd.Common.Points
import jakarta.persistence.*

@Entity
data class Snapshot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val time: String,

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    val activity: Activity,

    @Embedded
    val points: Points,
)