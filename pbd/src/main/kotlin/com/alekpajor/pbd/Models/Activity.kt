package com.alekpajor.pbd.Models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Activity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    val exercise: Exercise,

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    val patient: User,

    val duration: String,

    val startingTime: String,

    val period: String,
)