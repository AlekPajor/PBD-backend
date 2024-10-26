package com.alekpajor.pbd.Models

import com.alekpajor.pbd.Common.Points
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Snapshot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val time: String,

    @Embedded
    val points: Points,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    val exercise: Exercise? = null
)