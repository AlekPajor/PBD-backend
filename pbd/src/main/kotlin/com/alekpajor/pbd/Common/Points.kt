package com.alekpajor.pbd.Common

import jakarta.persistence.Embeddable

@Embeddable
data class Points(
    val leftWrist: Float,
    val rightWrist: Float,
    val leftElbow: Float,
    val rightElbow: Float,
    val leftAnkle: Float,
    val rightAnkle: Float,
)