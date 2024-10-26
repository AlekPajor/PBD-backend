package com.alekpajor.pbd.Models

import jakarta.persistence.*

@Entity
data class Activity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    val exercise: Exercise,

    val name: String,

    val duration: String,

    val startingTime: String,

    val period: String,
)