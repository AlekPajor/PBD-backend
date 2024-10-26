package com.alekpajor.pbd.Requests

data class AddSnapshotRequest(
    val time: String,
    val leftElbow: Int,
    val rightElbow: Int,
    val leftKnee: Int,
    val rightKnee: Int,
)
