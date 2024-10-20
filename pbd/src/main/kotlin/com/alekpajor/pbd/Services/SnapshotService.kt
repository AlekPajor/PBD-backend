package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Common.Points
import com.alekpajor.pbd.Models.Report
import com.alekpajor.pbd.Models.Snapshot
import com.alekpajor.pbd.Repository.ActivityRepository
import com.alekpajor.pbd.Repository.SnapshotRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SnapshotService(
    @Autowired val snapshotRepository: SnapshotRepository,
    @Autowired val activityRepository: ActivityRepository
) {
    fun getAllSnapshots(): List<Snapshot> = snapshotRepository.findAll()

    fun createSnapshot(snapshot: Snapshot): Snapshot = snapshotRepository.save(snapshot)

    fun addNewSnapshot(
        activityId: Long,
        time: String,
        leftWrist: Float,
        rightWrist: Float,
        leftElbow: Float,
        rightElbow: Float,
        leftAnkle: Float,
        rightAnkle: Float,
    ) {
        val activity = activityRepository.findById(activityId).orElseThrow {
            IllegalArgumentException("No activity found with ID: $activityId")
        }

        val points = Points(
            leftAnkle = leftAnkle,
            leftElbow = leftElbow,
            leftWrist = leftWrist,
            rightAnkle = rightAnkle,
            rightElbow = rightElbow,
            rightWrist = rightWrist,
        )
        val snapshot = Snapshot(
            activity = activity,
            time = time,
            points = points
        )

        snapshotRepository.save(snapshot)
    }
}