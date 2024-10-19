package com.alekpajor.pbd.Models

import jakarta.persistence.*

@Entity
data class Activity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val duration: String,

    val startingTime: String,

    val period: String,

    @OneToMany(mappedBy = "activity", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val snapshots: MutableList<Snapshot> = mutableListOf()
)