package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Models.Snapshot
import com.alekpajor.pbd.Repository.SnapshotRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SnapshotService(
    @Autowired val snapshotRepository: SnapshotRepository,
) {
    fun getAllSnapshots(): List<Snapshot> = snapshotRepository.findAll()

    fun createSnapshot(snapshot: Snapshot): Snapshot = snapshotRepository.save(snapshot)
}