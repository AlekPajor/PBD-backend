package com.alekpajor.pbd.Common

import jakarta.persistence.Embeddable

@Embeddable
data class Points(
    val leftElbow: Int,
    val rightElbow: Int,
    val leftKnee: Int,
    val rightKnee: Int
)