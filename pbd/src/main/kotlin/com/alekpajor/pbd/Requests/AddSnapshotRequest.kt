package com.alekpajor.pbd.Requests

data class AddSnapshotRequest(
    val activityId: Long,
    val time: String,
    val leftWrist: Float,
    val rightWrist: Float,
    val leftElbow: Float,
    val rightElbow: Float,
    val leftAnkle: Float,
    val rightAnkle: Float,
)
