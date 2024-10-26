package com.alekpajor.pbd.Models

import jakarta.persistence.*

@Entity
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @OneToMany(mappedBy = "exercise", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val snapshots: MutableList<Snapshot> = mutableListOf()
)