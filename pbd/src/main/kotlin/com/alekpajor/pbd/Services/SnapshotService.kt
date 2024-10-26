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
) {
    fun getAllSnapshots(): List<Snapshot> = snapshotRepository.findAll()

    fun createSnapshot(snapshot: Snapshot): Snapshot = snapshotRepository.save(snapshot)

    fun addNewSnapshot(
        time: String,
        leftElbow: Int,
        rightElbow: Int,
        leftKnee: Int,
        rightKnee: Int,
    ) {
        val points = Points(
            leftElbow = leftElbow,
            rightElbow = rightElbow,
            leftKnee = leftKnee,
            rightKnee = rightKnee,
        )
        val snapshot = Snapshot(
            time = time,
            points = points
        )

        snapshotRepository.save(snapshot)
    }
}